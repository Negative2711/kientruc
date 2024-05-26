package com.example.se.qlsv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.se.qlsv.dto.CustomMonAndSunRequestDTO.CustomMonAndSunRequestDTO;
import com.example.se.qlsv.dto.CustomPairInterruptTimeTableResponseDTO.CustomObjectSectionClassTimeTable;
import com.example.se.qlsv.dto.CustomPairInterruptTimeTableResponseDTO.PairOfInterruptTimeTable;
import com.example.se.qlsv.dto.CustomRegistrationSectionResponseDTO.CustomRegistrationSectionClassResponseDTO;
import com.example.se.qlsv.dto.CustomRegistrationSectionResponseDTO.CustomSpectorRegistrationSectionClassResponseDTO;
import com.example.se.qlsv.dto.CustomSectionClassGroupResponseDTO.CustomSectionClassGroupResponseDTO;
import com.example.se.qlsv.dto.CustomSectionClassGroupResponseDTO.CustomTimeTable;
import com.example.se.qlsv.dto.CustomSemesterPatternResponseDTO.CustomMySubjectIsPass;
import com.example.se.qlsv.dto.CustomSemesterPatternResponseDTO.CustomSemesterPatternResponseDTO;
import com.example.se.qlsv.dto.CustomSemesterPatternResponseDTO.SemesterPatternRequiredCredits;
import com.example.se.qlsv.dto.CustomStudentGradeReponseDTO.CustomSemesterStudentGrades;
import com.example.se.qlsv.dto.CustomStudentGradeReponseDTO.CustomStudentGradeReponseDTO;
import com.example.se.qlsv.dto.CustomStudentProgressResponseDTO.CustomStudentProgressResponseDTO;
import com.example.se.qlsv.dto.CustomTimeTableResponseDTO.CustomTimeTableResponseDTO;
import com.example.se.qlsv.entity.Grade;
import com.example.se.qlsv.entity.RegistrationSectionClass;
import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.SectionClassGroup;
import com.example.se.qlsv.entity.SemesterPattern;
import com.example.se.qlsv.entity.SemesterSchool;
import com.example.se.qlsv.entity.Student;
import com.example.se.qlsv.entity.Subject;
import com.example.se.qlsv.entity.TimeTable;
import com.example.se.qlsv.enumric.LessionDay;
import com.example.se.qlsv.enumric.SectionClassStatus;
import com.example.se.qlsv.enumric.SectionClassType;
import com.example.se.qlsv.repository.GradeRepository;
import com.example.se.qlsv.repository.RegistrationSectionClassRepository;
import com.example.se.qlsv.repository.SectionClassGroupRepository;
import com.example.se.qlsv.repository.SectionClassRepository;
import com.example.se.qlsv.repository.SemesterPatternRepository;
import com.example.se.qlsv.repository.SemesterSchoolRepository;
import com.example.se.qlsv.repository.StudentRepository;
import com.example.se.qlsv.repository.SubjectRepository;
import com.example.se.qlsv.repository.TimeTableRepository;
import com.example.se.qlsv.service.StudentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SemesterSchoolRepository semesterSchoolRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private SectionClassRepository sectionClassRepository;
	@Autowired
	private SectionClassGroupRepository sectionClassGroupRepository;
	@Autowired
	private TimeTableRepository timeTableRepository;
	@Autowired
	private RegistrationSectionClassRepository registrationSectionClassRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private SemesterPatternRepository semesterPatternRepository;
	
	@GetMapping("/findById/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable long studentId) {
		Student student = studentService.getStudentById(studentId);
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/getSemesterSchools")
	public ResponseEntity<?> getSemesterSchools() {
		List<SemesterSchool> semesterSchools = semesterSchoolRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		return ResponseEntity.ok(semesterSchools);
	}
	
	@PostMapping("/getSubjectBySemesterAndStudentMajor")
	public ResponseEntity<?> getSubjectBySemesterAndStudentMajor(@RequestBody Map<String, Object> requestBody) {
		Long semesterId = Long.parseLong(requestBody.get("semesterId").toString());
        Long studentId = Long.parseLong(requestBody.get("studentId").toString());
        
        
        Student s = studentRepository.findById(studentId).orElse(null);
        long majorId = s.getMajor().getId();
        
        List<Subject> subjects = subjectRepository.getSubjectBySemesterAndStudentMajor(semesterId, majorId);
		return ResponseEntity.ok(subjects);
	}
	
	@PostMapping("/getSectionClassesBySubjectAndSemester")
	public ResponseEntity<?> getSectionClassesBySubjectAndSemester(@RequestBody Map<String, Object> requestBody) {
		Long subjectId = Long.parseLong(requestBody.get("subjectId").toString());
		Long semesterId = Long.parseLong(requestBody.get("semesterId").toString());
		Long isFilterConflict = Long.parseLong(requestBody.get("isFilterConflict").toString());
		Long isFilterStatus = Long.parseLong(requestBody.get("isFilterStatus").toString());
		
		List<SectionClass> sectionClasses = sectionClassRepository.getSectionClassesBySubjectAndSemester(subjectId, semesterId);
		
		if(isFilterConflict == 0 && isFilterStatus == 0) {// Nếu ko filter gì cả thì xử lý data như api trên
			return ResponseEntity.ok(sectionClasses);
		}
		
		if(isFilterConflict != 0) {// Tiến hành filter conflict
			for (int i = sectionClasses.size() - 1; i >= 0; i--) {
				SectionClass sectionClass = sectionClasses.get(i);
				Set<SectionClassGroup> sectionClassGroups = sectionClass.getSectionClassGroups();
				boolean test = checkConflict(sectionClassGroups, requestBody);
				if(test) {
					sectionClasses.remove(i);
				}
			}
		}
		
		if(isFilterStatus != 0) {
			for(int i=0; i<sectionClasses.size(); i++) {
				SectionClass sectionClass = sectionClasses.get(i);
				SectionClassStatus lockStatus = sectionClass.getLockStatus();
				if(!lockStatus.equals(SectionClassStatus.DANG_CHO_SINH_VIEN_DANG_KY)) {
					sectionClasses.remove(i);
				}
			}
		}
		
		return ResponseEntity.ok(sectionClasses);
	}
	
	@PostMapping("/getSectionClassFilterPineline")
	public ResponseEntity<?> getSectionClassFilterPineline(@RequestBody Map<String, Object> requestBody) {
		Long subjectId = Long.parseLong(requestBody.get("subjectId").toString());
		Long semesterId = Long.parseLong(requestBody.get("semesterId").toString());
		Long isFilterConflict = Long.parseLong(requestBody.get("isFilterConflict").toString());
		Long isFilterStatus = Long.parseLong(requestBody.get("isFilterStatus").toString());
		List<SectionClass> sectionClasses = sectionClassRepository.getSectionClassesBySubjectAndSemester(subjectId, semesterId);
		
		if(isFilterConflict == 0 && isFilterStatus == 0) {// Nếu ko filter gì cả thì xử lý data như api trên
			return ResponseEntity.ok(sectionClasses);
		}
		
		if(isFilterConflict != 0) {// Tiến hành filter conflict
			for (int i = sectionClasses.size() - 1; i >= 0; i--) {
				SectionClass sectionClass = sectionClasses.get(i);
				Set<SectionClassGroup> sectionClassGroups = sectionClass.getSectionClassGroups();
				boolean test = checkConflict(sectionClassGroups, requestBody);
				if(test) {
					sectionClasses.remove(i);
				}
			}
		}
		
		if(isFilterStatus != 0) {
			for(int i=0; i<sectionClasses.size(); i++) {
				SectionClass sectionClass = sectionClasses.get(i);
				SectionClassStatus lockStatus = sectionClass.getLockStatus();
				if(!lockStatus.equals(SectionClassStatus.DANG_CHO_SINH_VIEN_DANG_KY)) {
					sectionClasses.remove(i);
				}
			}
		}
		
		return ResponseEntity.ok(sectionClasses);
	}
	
	private boolean checkConflict(Set<SectionClassGroup> sectionClassGroups, Map<String, Object> requestBody) {
		Long studentId = Long.parseLong(requestBody.get("studentId").toString());
		Long semesterId = Long.parseLong(requestBody.get("semesterId").toString());
		
		// Kiểm tra sv có lịch nào trong học kì không
		List<TimeTable> studentTimeTables = timeTableRepository.getStudentTimeTables(studentId, semesterId);
		if(studentTimeTables.size() <= 0) // Nếu sv chưa có đăng ký môn nào thì khỏi lo sợ conflict
			return false;
		
		// Kiểm tra timeTables của từng sectionClassGroup so vs timeTables của student
		for (SectionClassGroup sectionClassGroup : sectionClassGroups) {
			long sectionClassGroupId = sectionClassGroup.getId();
			List<TimeTable> sectionClassGroupTimeTables = timeTableRepository.getTimeTablesBySectionClassGroup(sectionClassGroupId);
			for (TimeTable sectionClassGroupTimeTable : sectionClassGroupTimeTables) {// Source
				LessionDay sLessionDay = sectionClassGroupTimeTable.getLessionDay();
				int sStartLesstionTime = Integer.parseInt(sectionClassGroupTimeTable.getStartLessionTime().toString().substring(1));
				int sEndLesstionTime = Integer.parseInt(sectionClassGroupTimeTable.getEndLessionTime().toString().substring(1));
				for (TimeTable studentTimeTable : studentTimeTables) {// Destination
					LessionDay dLessionDay = studentTimeTable.getLessionDay();
					int dStartLesstionTime = Integer.parseInt(studentTimeTable.getStartLessionTime().toString().substring(1));
					int dEndLesstionTime = Integer.parseInt(studentTimeTable.getStartLessionTime().toString().substring(1));
					if(sLessionDay.equals(dLessionDay)) {
						if( (dStartLesstionTime >= sStartLesstionTime && dStartLesstionTime <= sEndLesstionTime) || (dEndLesstionTime >= sStartLesstionTime) ) {
							return true;
						}
					}
				}
			}			
		}	
		return false;
	}

	@PostMapping("/getSectionClassGroupBySectionClass")
	public ResponseEntity<?> getSectionClassGroupBySectionClass(@RequestBody Map<String, Object> requestBody) {
		Long sectionClassId = Long.parseLong(requestBody.get("sectionClassId").toString());
		List<SectionClassGroup> sectionClassesGroup = sectionClassGroupRepository.getSectionClassGroupBySectionClass(sectionClassId);
		
		List<CustomSectionClassGroupResponseDTO> customSectionClassGroupResponseDTOs = new ArrayList<CustomSectionClassGroupResponseDTO>();
		for (SectionClassGroup sectionClassGroup : sectionClassesGroup) {
			Set<TimeTable> timeTables = sectionClassGroup.getTimeTables();
			// Lọc unique timetable
			List<CustomTimeTable> customTimeTables = new ArrayList<CustomTimeTable>();
			Map<String, Boolean> typeMap = new HashMap<>();
			for (TimeTable timeTable : timeTables) {
				 if (!typeMap.containsKey(timeTable.getLessionType().toString())) {
	                typeMap.put(timeTable.getLessionType().toString(), true);
	                CustomTimeTable customTimeTable = CustomTimeTable
							.builder()
							.timeTable(timeTable)	
							.teacher(timeTable.getTeacher())
							.build();
	                customTimeTables.add(customTimeTable);
	            }
			}
			
			// Đếm số sinh viên trong từng nhóm
			int numberOfStudentRegisteredInSectionClassGroup = 0;
//			int size = sectionClassGroup.getSectionClass().getRegistrationSectionClasses().size();
			long sectionClassGroupId = sectionClassGroup.getId();
			Set<RegistrationSectionClass> registrationSectionClasses = sectionClassGroup.getSectionClass().getRegistrationSectionClasses();
			for (RegistrationSectionClass registrationRecord : registrationSectionClasses) {
				long studentRegisterSectionGroupId = registrationRecord.getSectionClassGroup().getId();
				if(studentRegisterSectionGroupId == sectionClassGroupId) {
					numberOfStudentRegisteredInSectionClassGroup++;
				}
			}
			
			CustomSectionClassGroupResponseDTO customSectionClassGroupResponseDTO = CustomSectionClassGroupResponseDTO
					.builder()
					.sectionClassGroup(sectionClassGroup)
					.timeTables(customTimeTables)
					.sectionClass(sectionClassGroup.getSectionClass())
					.registeredNumber(numberOfStudentRegisteredInSectionClassGroup)
					.build();
			customSectionClassGroupResponseDTOs.add(customSectionClassGroupResponseDTO);
		}
		
		return ResponseEntity.ok(customSectionClassGroupResponseDTOs);
	}
	
	@GetMapping("/getSectionClassById/{sectionClassId}")
	public SectionClass getSectionClassById(@PathVariable long sectionClassId) {
		return sectionClassRepository.findById(sectionClassId).orElse(null);
	}
	
	@PostMapping("/getInterruptTimeTablesBySectionClass")
	public ResponseEntity<?> getInterruptTimeTablesBySectionClass(@RequestBody Map<String, Object> requestBody) {
		Long sectionClassId = Long.parseLong(requestBody.get("sectionClassId").toString());
		Long studentId = Long.parseLong(requestBody.get("studentId").toString());
		Long semesterId = Long.parseLong(requestBody.get("semesterId").toString());
		System.out.println("test===================================================" + sectionClassId + "_" + studentId + "_" + semesterId);
		
		// Lấy ra ds tkb của sinh viên có
		List<TimeTable> studentTimeTables = timeTableRepository.getStudentTimeTables(studentId, semesterId);
		// Lấy ra ds tkb của lhp sinh viên chọn
		List<TimeTable> sectionClassTimeTables = timeTableRepository.getTimeTablesBySectionClass(sectionClassId);
		List<PairOfInterruptTimeTable> conflictTimeTables = new ArrayList<PairOfInterruptTimeTable>();
		for (TimeTable sectionClassTimeTable : sectionClassTimeTables) {// Source
			LessionDay sLessionDay = sectionClassTimeTable.getLessionDay();
			int sStartLesstionTime = Integer.parseInt(sectionClassTimeTable.getStartLessionTime().toString().substring(1));
			int sEndLesstionTime = Integer.parseInt(sectionClassTimeTable.getEndLessionTime().toString().substring(1));
			for (TimeTable studentTimeTable : studentTimeTables) {// Destination
				LessionDay dLessionDay = studentTimeTable.getLessionDay();
				int dStartLesstionTime = Integer.parseInt(studentTimeTable.getStartLessionTime().toString().substring(1));
				int dEndLesstionTime = Integer.parseInt(studentTimeTable.getStartLessionTime().toString().substring(1));
				if(sLessionDay.equals(dLessionDay)) {
					if( (dStartLesstionTime >= sStartLesstionTime && dStartLesstionTime <= sEndLesstionTime) || (dEndLesstionTime >= sStartLesstionTime) ) {
						CustomObjectSectionClassTimeTable sectionClassConflictObject = CustomObjectSectionClassTimeTable
								.builder()
								.sectionClass(sectionClassTimeTable.getSectionClassGroup().getSectionClass())
								.subject(sectionClassTimeTable.getSectionClassGroup().getSectionClass().getSubject())
								.timeTable(sectionClassTimeTable)
								.build();
						CustomObjectSectionClassTimeTable studentConflictObject = CustomObjectSectionClassTimeTable
								.builder()
								.sectionClass(studentTimeTable.getSectionClassGroup().getSectionClass())
								.subject(studentTimeTable.getSectionClassGroup().getSectionClass().getSubject())
								.timeTable(studentTimeTable)
								.build();
						PairOfInterruptTimeTable pairOfInterruptTimeTable = PairOfInterruptTimeTable
								.builder()
								.sectionClassConflictObject(sectionClassConflictObject)
								.studentConflictObject(studentConflictObject)
								.build();
						conflictTimeTables.add(pairOfInterruptTimeTable);
					}
				}
			}
		}
		return ResponseEntity.ok(conflictTimeTables);
	}
	
	@PostMapping("/getStudentRegistrationSectionClass")
	public ResponseEntity<?> getStudentRegistrationSectionClass(@RequestBody Map<String, Object> requestBody) {
		Long registrationSectionClassId = Long.parseLong(requestBody.get("registrationSectionClassId").toString());
		RegistrationSectionClass registrationSectionClass = registrationSectionClassRepository.findById(registrationSectionClassId).orElse(null);
		CustomSpectorRegistrationSectionClassResponseDTO customDTO = CustomSpectorRegistrationSectionClassResponseDTO
				.builder()
				.sectionClass(registrationSectionClass.getSectionClass())
				.sectionClassGroup(registrationSectionClass.getSectionClassGroup())
				.timeTables(registrationSectionClass.getSectionClassGroup().getTimeTables())
				.teacher(registrationSectionClass.getSectionClass().getTeacher())
				.build();
		return ResponseEntity.ok(customDTO);
	}
	
	@PostMapping("/getStudentRegistrationSectionClassesBySemester")
	public ResponseEntity<?> getStudentRegistrationSectionClassesBySemester(@RequestBody Map<String, Object> requestBody) {
		Long studentId = Long.parseLong(requestBody.get("studentId").toString());
		Long semesterId = Long.parseLong(requestBody.get("semesterId").toString());
		List<RegistrationSectionClass> studentRegistrationSectionClasses = registrationSectionClassRepository.getStudentRegistrationSectionClassesBySemester(studentId, semesterId);
		List<CustomRegistrationSectionClassResponseDTO> customRegistrationSectionClassResponseDTOs = new ArrayList<CustomRegistrationSectionClassResponseDTO>();
		for (RegistrationSectionClass registrationSectionClass : studentRegistrationSectionClasses) {
			CustomRegistrationSectionClassResponseDTO customRegistrationSectionClassResponseDTO = CustomRegistrationSectionClassResponseDTO
					.builder()
					.registrationSectionClass(registrationSectionClass)
					.sectionClass(registrationSectionClass.getSectionClass())
					.subject(registrationSectionClass.getSectionClass().getSubject())
					.build();
			customRegistrationSectionClassResponseDTOs.add(customRegistrationSectionClassResponseDTO);
		}
		return ResponseEntity.ok(customRegistrationSectionClassResponseDTOs);
	}
	
	@PostMapping("/registerRegistrationSectionClass")
	public ResponseEntity<?> registerRegistrationSectionClass(@RequestBody Map<String, Object> requestBody) {
		Long studentId = Long.parseLong(requestBody.get("studentId").toString());
		Long sectionClassGroupId = Long.parseLong(requestBody.get("sectionClassGroupId").toString());
		Long semesterId = Long.parseLong(requestBody.get("semesterId").toString());
		
		// 01. Đã đăng ký or Đã học rồi
		// 02. LHP đã khóa
		// 03. Kiểm tra có timeTables hay không
		// 04. Kiểm tra preRequiredSubjectId
		// 05. Kiểm tra số lượng max của sectionClassGroupId
		// 06. Trùng lịch
		
		SectionClassGroup sectionClassGroup = sectionClassGroupRepository.findById(sectionClassGroupId).orElse(null);
		SectionClass sectionClass = sectionClassGroup.getSectionClass();
		Subject subject = sectionClass.getSubject();
		
		// 01. Nếu học mới mà trong quá khứ đã từng học subject đó rồi thì LHP hnay đăng ký phải là học lại mới cho đăng ký.
		List<RegistrationSectionClass> listRegistrationClassesOfStudent = registrationSectionClassRepository.getStudentRegistrationSectionClassesBySubject(studentId, sectionClass.getSubject().getId());
		if(sectionClass.getSectionClassType().equals(SectionClassType.HOC_MOI) && listRegistrationClassesOfStudent.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bạn đã hoàn thành môn học này trước đây hoặc đã đăng ký.");
		}
		
		// 02. LHP đã khóa
		if(sectionClass.getLockStatus().equals(SectionClassStatus.DA_KHOA) || sectionClass.getLockStatus().equals(SectionClassStatus.CHAP_NHAN_MO_LOP)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lớp học phần đã ở trạng thái khóa.");
		}
		
		// 03. Kiểm tra có timeTable hay không
		Set<TimeTable> timeTables = sectionClassGroup.getTimeTables();
		if(timeTables.size() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lớp học phần đang lên kế hoạch thời khóa biểu.");
		}
		
		// 04. Kiểm tra preRequiredSubjectId
		long preRequiredSubjectId = subject.getPreRequiredSubjectId();
		if(preRequiredSubjectId != 0) {// Nếu học phần tiên quyết có mã
			int count = gradeRepository.getCountStudentPassPreRequiredSubject(studentId, preRequiredSubjectId);
			if(count <= 0) {
				Subject preRequiredSujbect = subjectRepository.findById(preRequiredSubjectId).orElse(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Học phần tiên quyết: "+ preRequiredSujbect.getSubjectName() +" chưa hoàn thành");
			}	
		}
		
		// 05. Kiểm tra số lượng max của sectionClassGroupId
		int studentsRegisteredBefore = registrationSectionClassRepository.getCountStudentBySectionClassGroup(sectionClassGroupId);
		int maxStudent = sectionClassGroup.getMaxStudent();
		if(studentsRegisteredBefore >= maxStudent) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nhóm học phần đã đủ số lượng. Vui lòng chọn nhóm HP khác");
		}
		if(studentsRegisteredBefore > sectionClassGroup.getMaxStudent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lớp học phần đã đủ số lượng sinh viên đăng ký.");
		}
		// 06. Kiểm tra Trùng lịch
		List<TimeTable> sectionClassGroupTimeTables = timeTableRepository.getTimeTablesBySectionClassGroup(sectionClassGroupId);
		List<TimeTable> studentTimeTables = timeTableRepository.getStudentTimeTables(studentId, semesterId);
		for (TimeTable sectionClassGroupTimeTable : sectionClassGroupTimeTables) {// Source
			LessionDay sLessionDay = sectionClassGroupTimeTable.getLessionDay();
			int sStartLesstionTime = Integer.parseInt(sectionClassGroupTimeTable.getStartLessionTime().toString().substring(1));
			int sEndLesstionTime = Integer.parseInt(sectionClassGroupTimeTable.getEndLessionTime().toString().substring(1));
			for (TimeTable studentTimeTable : studentTimeTables) {// Destination
				LessionDay dLessionDay = studentTimeTable.getLessionDay();
				int dStartLesstionTime = Integer.parseInt(studentTimeTable.getStartLessionTime().toString().substring(1));
				int dEndLesstionTime = Integer.parseInt(studentTimeTable.getStartLessionTime().toString().substring(1));
				if(sLessionDay.equals(dLessionDay)) {
					if( (dStartLesstionTime >= sStartLesstionTime && dStartLesstionTime <= sEndLesstionTime) || (dEndLesstionTime >= sStartLesstionTime) ) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nhóm học phần bị trùng lịch với môn khác bạn đã đăng ký.");
					}
				}
			}
		}
		
		// -> Tiến hành đăng ký
		Student newStudent = studentService.getStudentById(studentId);
		SectionClassGroup newSectionClassGroup = sectionClassGroupRepository.findById(sectionClassGroupId).orElse(null);
		SectionClass newSectionClass = sectionClassGroup.getSectionClass();
		RegistrationSectionClass newRegistrationSectionClass = RegistrationSectionClass
				.builder()
				.student(newStudent)
				.sectionClassGroup(newSectionClassGroup)
				.sectionClass(newSectionClass)
				.classGroupName(newSectionClassGroup.getClassGroupName())
				.registerDate(new Date())
				.build();
		RegistrationSectionClass saved = registrationSectionClassRepository.save(newRegistrationSectionClass);
		
		// 01. Cập nhật Sanh ra công nợ
		// 02. Cập nhật registered_number + 1
		sectionClassRepository.updateRegisteredNumberPlus1(sectionClass.getId());
		return ResponseEntity.ok(saved);// Phải trả về 1 json obj vì content-type bên client yêu cầu là application/json
	}
	
	@DeleteMapping("/destroyRegistration/{registrationSectionClassId}")
	public ResponseEntity<?> destroyRegistrationSectionClass(@PathVariable long registrationSectionClassId) {
		try {
			// 01. Kiểm tra trạng thái LHP Chấp nhận mở lớp hay đã khóa chưa
			// 02. Kiểm tra đã đóng tiền chưa
			RegistrationSectionClass registrationSectionClass = registrationSectionClassRepository.findById(registrationSectionClassId).orElse(null);
			SectionClass sectionClass = registrationSectionClass.getSectionClass();
			
			if(sectionClass.getLockStatus().equals(SectionClassStatus.CHAP_NHAN_MO_LOP) || sectionClass.getLockStatus().equals(SectionClassStatus.DA_KHOA)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lớp học phần đã ở trạng thái Chấp nhận mở lớp hoặc Đã khóa se không thể hủy");
			}
			if(registrationSectionClass.getPaid() > 0) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bạn đã đóng tiền cho học phần này rồi. Không thể hủy!");
			}
			// 03. Tiến hành hủy. Update registered_student - 1
			long sectionClassId = sectionClass.getId();
			sectionClassRepository.updateRegisteredNumberMinus1(sectionClassId);
			registrationSectionClassRepository.deleteById(registrationSectionClassId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok("OK");
	}
	
	@PostMapping("/getTimeTablesByMonAndSun")
	@Cacheable("timeTablesCache")
	public List<CustomTimeTableResponseDTO> getTimeTablesByMonAndSun(@RequestBody CustomMonAndSunRequestDTO requestDTO) {
		long studentId = requestDTO.getStudentId();
		Date mondayDate = requestDTO.getMondayDate();
		Date sundayDate = requestDTO.getSundayDate();
		String lessionType = requestDTO.getLessionType();
		System.out.println("studentId=" + requestDTO.getStudentId());
		System.out.println("mondayDate=" + requestDTO.getMondayDate());
		System.out.println("sundayDate=" + requestDTO.getSundayDate());
		System.out.println("lessionType=" + requestDTO.getLessionType());
		
		List<CustomTimeTableResponseDTO> timeTablesDTO = new ArrayList<CustomTimeTableResponseDTO>();
		
		if (lessionType != null && !lessionType.trim().isEmpty()) {
	        // Kiểm tra lessionType có giá trị và không phải là chuỗi rỗng
			if(lessionType.equalsIgnoreCase("THI")) {// Lấy ra timetables ko phải thi
				List<TimeTable> timeTablesThi = timeTableRepository.getTimeTablesByMonAndSunFilterExaming(studentId, mondayDate, sundayDate);
				for (TimeTable timeTable : timeTablesThi) {
					CustomTimeTableResponseDTO timeTableDTO = CustomTimeTableResponseDTO
							.builder()
							.timeTable(timeTable)
							.sectionClass(timeTable.getSectionClassGroup().getSectionClass())
							.subject(timeTable.getSectionClassGroup().getSectionClass().getSubject())
							.teacher(timeTable.getSectionClassGroup().getSectionClass().getTeacher())
							.build();
					timeTablesDTO.add(timeTableDTO);	
				}
				return timeTablesDTO;
			}
			// Lấy ra timetables ko phải thi
			List<TimeTable> timeTablesNotThi = timeTableRepository.getTimeTablesByMonAndSunFilterNotExaming(studentId, mondayDate, sundayDate);
			for (TimeTable timeTable : timeTablesNotThi) {
				CustomTimeTableResponseDTO timeTableDTO = CustomTimeTableResponseDTO
						.builder()
						.timeTable(timeTable)
						.sectionClass(timeTable.getSectionClassGroup().getSectionClass())
						.subject(timeTable.getSectionClassGroup().getSectionClass().getSubject())
						.teacher(timeTable.getSectionClassGroup().getSectionClass().getTeacher())
						.build();
				timeTablesDTO.add(timeTableDTO);	
			}
			return timeTablesDTO;
	    }
		// Lấy ra timetable all
		List<TimeTable> timeTablesByMonAndSun = timeTableRepository.getTimeTablesByMonAndSun(studentId, mondayDate, sundayDate);
		for (TimeTable timeTable : timeTablesByMonAndSun) {
			CustomTimeTableResponseDTO timeTableDTO = CustomTimeTableResponseDTO
					.builder()
					.timeTable(timeTable)
					.sectionClass(timeTable.getSectionClassGroup().getSectionClass())
					.subject(timeTable.getSectionClassGroup().getSectionClass().getSubject())
					.teacher(timeTable.getSectionClassGroup().getSectionClass().getTeacher())
					.build();
			timeTablesDTO.add(timeTableDTO);
		}
	    return timeTablesDTO;
	}
	
	@PostMapping("/getStudentRegistrationSectionClasses")
	public ResponseEntity<?> getStudentRegistrationSectionClasses(@RequestBody Map<String, Object> requestBody) {
		System.out.println("getStudentRegistrationSectionClasses was calledddddddddddddddddddddddddddddddddd!");
		Long studentId = Long.parseLong(requestBody.get("studentId").toString());
		List<RegistrationSectionClass> studentRegistrationSectionClasses = registrationSectionClassRepository.getStudentRegistrationSectionClasses(studentId);
		List<CustomRegistrationSectionClassResponseDTO> list = new ArrayList<CustomRegistrationSectionClassResponseDTO>();
		for (RegistrationSectionClass registrationSectionClass : studentRegistrationSectionClasses) {
			CustomRegistrationSectionClassResponseDTO customMoreObjectReturn = CustomRegistrationSectionClassResponseDTO
					.builder()
					.registrationSectionClass(registrationSectionClass)
					.sectionClass(registrationSectionClass.getSectionClass())
					.subject(registrationSectionClass.getSectionClass().getSubject())
					.semesterSchool(registrationSectionClass.getSectionClass().getSemesterSchool())
					.build();
			list.add(customMoreObjectReturn);
		}
	    return ResponseEntity.ok(list);
	}
	
	@PostMapping("/getGradeByRegistrationSectionClass")
	public ResponseEntity<?> getGradeByRegistrationSectionClass(@RequestBody Map<String, Object> requestBody) {
		Long registrationSectionClassId = Long.parseLong(requestBody.get("registrationSectionClassId").toString());
		Grade studentGrade = gradeRepository.getGradeByRegistrationSectionClass(registrationSectionClassId);
		System.out.println("studentGrade==================" + studentGrade);
		if(studentGrade == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NULL");
		}
		return ResponseEntity.ok(studentGrade);
	}
	
	@PostMapping("/getStudentProgress")
	public ResponseEntity<?> getStudentProgress(@RequestBody Map<String, Object> requestBody) {
		Long studentId = Long.parseLong(requestBody.get("studentId").toString());
		Student studentData = studentService.getStudentById(studentId);
		
		int requiredCredits = studentData.getMajor().getRequiredCredits();
		List<RegistrationSectionClass> registrationSectionClassesStudentPassed = registrationSectionClassRepository.getRegistrationSectionClassesStudentPassed(studentId);
		int currentCredits = 0;
		for (RegistrationSectionClass registrationSectionClass : registrationSectionClassesStudentPassed) {
			currentCredits += registrationSectionClass.getSectionClass().getSubject().getCredits();
		}
		CustomStudentProgressResponseDTO customStudentProgressResponseDTO = CustomStudentProgressResponseDTO
				.builder()
				.currentCredits(currentCredits)
				.requiredCredits(requiredCredits)
				.build();
		return ResponseEntity.ok(customStudentProgressResponseDTO);
	}
	
	@PostMapping("/getStudentGrades")
	public ResponseEntity<?> getStudentGrades(@RequestBody Map<String, Object> requestBody) {
		Long studentId = Long.parseLong(requestBody.get("studentId").toString());
		
		
		Student studentData = studentService.getStudentById(studentId); // Lấy ra số year mà student học *2 ra được tổng số học kỳ pattern
		int numberOfSemesterPattern = (int) (studentData.getYearOfStudy() * 2);
		
		// Tạo ra 1 mảng chứa các điểm sv theo các học kỳ pattern: HK1: {}, HK2: {},....HK9: {}
		List<CustomSemesterStudentGrades> semesterPatternStudentGrades = new ArrayList<CustomSemesterStudentGrades>();
		for (int i = 1; i <= numberOfSemesterPattern; i++) {
			
			
			List<Grade> studentGrades = gradeRepository.getStudentGradesBySemesterPattern(studentId, i); // Lấy ra list điểm sv đang hoặc đã từng học trong RegistrationSectionClass
			List<CustomStudentGradeReponseDTO> customStudentGradeReponseDTOs = new ArrayList<CustomStudentGradeReponseDTO>();
			for (Grade grade : studentGrades) {// Vòng lặp này chỉ duyệt qua để peperline data. Ko có gì ghê cả
				CustomStudentGradeReponseDTO customStudentGradeReponseDTO = CustomStudentGradeReponseDTO
						.builder()
						.grade(grade)
						.sectionClass(grade.getRegistrationSectionClass().getSectionClass())
						.subject(grade.getRegistrationSectionClass().getSectionClass().getSubject())
						.build();
				customStudentGradeReponseDTOs.add(customStudentGradeReponseDTO);
			}
			
			// Sau đó ta tạo ra 1 object custom chứa semesterPatternId đó kèm đống điểm của sv
			SemesterPattern semesterPattern = semesterPatternRepository.findById(Long.parseLong(""+i)).orElse(null);
			CustomSemesterStudentGrades customSemesterStudentGrades = CustomSemesterStudentGrades
					.builder()
					.semesterPattern(semesterPattern)
					.studentGrades(customStudentGradeReponseDTOs)
					.build();
			semesterPatternStudentGrades.add(customSemesterStudentGrades);
		}
		return ResponseEntity.ok(semesterPatternStudentGrades);
	}
	
	@PostMapping("/getSemesterPatternsByStudent")
	public ResponseEntity<?> getSemesterPatternsByStudent(@RequestBody Map<String, Object> requestBody) {
		Long studentId = Long.parseLong(requestBody.get("studentId").toString());
		Student studentData = studentService.getStudentById(studentId); // Lấy ra số year mà student học *2 ra được tổng số học kỳ pattern
		long studentMajorId = studentData.getMajor().getId();
		int numberOfSemesterPattern = (int) (studentData.getYearOfStudy() * 2);
		
		List<SemesterPatternRequiredCredits> semesters = new ArrayList<SemesterPatternRequiredCredits>();
		int totalAccquiredSujectCredits = 0;
		int totalOptionalSubjectCredits = 0;
		int totalRequiredCredits = studentData.getMajor().getRequiredCredits();
		
		List<Subject> studentPassedSubjects = subjectRepository.getSubjectStudentPassed(studentId);
		for (int i = 1; i <= numberOfSemesterPattern; i++) {
			// Giải thuật:
			// 01. Lấy ra tất cả subjectId mà student đã hoàn thành (học xong và có passed)
			// 02. Lấy ra tất cả subjects by majorId and semesterPatternId
			// 03. Duyệt qua từng object xem coi có object nào passed thì mark done
			int totalAccquiredCredits = subjectRepository.getAccquiredCreditsBySemesterPatternAndMajor(i, studentMajorId);
			int totalOptionalCredits = subjectRepository.getOptionalCreditsBySemesterPatternAndMajor(i, studentMajorId);
			
			totalAccquiredSujectCredits += totalAccquiredCredits;
			totalOptionalSubjectCredits += totalOptionalCredits;
			
			List<Subject> accquiredSubjects = subjectRepository.getAccquiredSubjectsBySemesterPatternAndMajor(i, studentMajorId);
			List<Subject> optionalSubjects = subjectRepository.getOptionalSubjectsBySemesterPatternAndMajor(i, studentMajorId);
			// Duyệt qua các subject trong mảng subject để update isPass
			List<CustomMySubjectIsPass> newAccquiredSubjects = new ArrayList<CustomMySubjectIsPass>();
			for (Subject accquiredSubject : accquiredSubjects) {
				boolean isPass = false;
				if(checkSubjectIsPass(accquiredSubject, studentPassedSubjects)) {// Nếu trả về true tức: Passed -> update lại isPass cho accquiredSubject đó
					isPass = true;
				} 
				CustomMySubjectIsPass customMySubjectIsPass = CustomMySubjectIsPass
						.builder()
						.subject(accquiredSubject)
						.isPass(isPass)
						.build();
				newAccquiredSubjects.add(customMySubjectIsPass);
			}
			// Duyệt qua các subject trong mảng subject để update isPass
			List<CustomMySubjectIsPass> newOptionalSubjects = new ArrayList<CustomMySubjectIsPass>();
			for (Subject optionalSubject : optionalSubjects) {
				boolean isPass = false;
				if(checkSubjectIsPass(optionalSubject, studentPassedSubjects)) {// Nếu trả về true tức: Passed -> update lại isPass cho accquiredSubject đó
					isPass = true;
				} 
				CustomMySubjectIsPass customMySubjectIsPass = CustomMySubjectIsPass
						.builder()
						.subject(optionalSubject)
						.isPass(isPass)
						.build();
				newOptionalSubjects.add(customMySubjectIsPass);
			}
			
			int totalSubjectCredits = subjectRepository.getTotalCreditsBySemesterPatternAndMajor(i, studentMajorId);
			SemesterPatternRequiredCredits semesterPatternRequiredCredits = SemesterPatternRequiredCredits
					.builder()
					.semesterPatternId(i)
					.stageSemester("Học kỳ " + i)
					.totalRequiredCredits(totalSubjectCredits)
					.totalAccquiredCredits(totalAccquiredCredits)
					.totalOptionalCredits(totalOptionalCredits)
					.accquiredSubjects(newAccquiredSubjects)
					.optionalSubjects(newOptionalSubjects)
					.build();
			semesters.add(semesterPatternRequiredCredits);
		}
		
		CustomSemesterPatternResponseDTO semesterPatternResponseDTO = CustomSemesterPatternResponseDTO
				.builder()
				.semesters(semesters)
				.totalAccquiredSujectCredits(totalAccquiredSujectCredits)
				.totalOptionalSubjectCredits(totalOptionalSubjectCredits)
				.totalRequiredCredits(totalRequiredCredits)
				.build();
		return ResponseEntity.ok(semesterPatternResponseDTO);
	}

	private boolean checkSubjectIsPass(Subject subject, List<Subject> studentPassedSubjects) {
		for (Subject passedSubject : studentPassedSubjects) {
			if(subject.getId() == passedSubject.getId()) {
				return true;
			}
		}
		return false;
	}
}
