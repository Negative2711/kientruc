package com.example.se.qlsv.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.se.qlsv.dto.SectionClassDTO;
import com.example.se.qlsv.dto.TimeTableDTO;
import com.example.se.qlsv.dto.CustomSectionClassGroupResponseDTO.CustomSectionClassGroupResponseDTO;
import com.example.se.qlsv.dto.CustomSectionClassReponseDTO.CustomSectionClassResponseDTO;
import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.SectionClassGroup;
import com.example.se.qlsv.entity.SemesterSchool;
import com.example.se.qlsv.entity.Subject;
import com.example.se.qlsv.entity.Teacher;
import com.example.se.qlsv.entity.TimeTable;
import com.example.se.qlsv.enumric.ClassGroupName;
import com.example.se.qlsv.enumric.LessionType;
import com.example.se.qlsv.enumric.SectionClassType;
import com.example.se.qlsv.repository.SectionClassGroupRepository;
import com.example.se.qlsv.repository.SectionClassRepository;
import com.example.se.qlsv.repository.SemesterSchoolRepository;
import com.example.se.qlsv.repository.SubjectRepository;
import com.example.se.qlsv.repository.TeacherRepository;
import com.example.se.qlsv.repository.TimeTableRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private SectionClassRepository sectionClassRepository;
	@Autowired
	private SectionClassGroupRepository sectionClassGroupRepository;
	@Autowired
	private TimeTableRepository timeTableRepository;
	@Autowired
	private SemesterSchoolRepository semesterSchoolRepository;
	
	@GetMapping("/subject/list")
	public ResponseEntity<?> getSubjects() {
		List<Subject> subjects = subjectRepository.findAll();
		return ResponseEntity.ok(subjects);
	}
	
	@GetMapping("/teacher/list")
	public ResponseEntity<?> getTeachers() {
		List<Teacher> teachers = teacherRepository.findAll();
		return ResponseEntity.ok(teachers);
	}
	
	@GetMapping("/semesterSchool/list")
	public ResponseEntity<?> getSemesterSchools() {
		List<SemesterSchool> semesterSchools = semesterSchoolRepository.findAll();
		return ResponseEntity.ok(semesterSchools);
	}
	
	@GetMapping("/sectionClassGroup/list")
	public ResponseEntity<?> getSectionClassesGroup() {
		List<SectionClassGroup> sectionClassesGroup = sectionClassGroupRepository.findAll();
		List<CustomSectionClassGroupResponseDTO> customListSectionClassGroupDTO = new ArrayList<CustomSectionClassGroupResponseDTO>();
		for (SectionClassGroup sectionClassGroup : sectionClassesGroup) {
			CustomSectionClassGroupResponseDTO customSectionClassGroupDTO = CustomSectionClassGroupResponseDTO
					.builder()
					.sectionClassGroup(sectionClassGroup)
					.sectionClass(sectionClassGroup.getSectionClass())
					.build();
			customListSectionClassGroupDTO.add(customSectionClassGroupDTO);
		}
		return ResponseEntity.ok(customListSectionClassGroupDTO);
	}
	
	@GetMapping("/sectionClass/list")
	public ResponseEntity<?> getSectionClasses() {
		List<CustomSectionClassResponseDTO> sectionClassResponseDTOs = new ArrayList<CustomSectionClassResponseDTO>();
		List<SectionClass> sectionClasses = sectionClassRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		for (SectionClass sectionClass : sectionClasses) {
			CustomSectionClassResponseDTO sectionClassResponseDTO = CustomSectionClassResponseDTO
					.builder()
					.sectionClass(sectionClass)
					.teacher(sectionClass.getTeacher())
					.subject(sectionClass.getSubject())
					.semesterSchool(sectionClass.getSemesterSchool())
					.build();
			sectionClassResponseDTOs.add(sectionClassResponseDTO);
		}
		return ResponseEntity.ok(sectionClassResponseDTOs);
	}
	
	@PostMapping("/sectionClass/save")
	public ResponseEntity<?> saveSectionClass(@RequestBody SectionClassDTO sectionClassDTO) {
		SectionClass sectionClass = SectionClass
				.builder()
				.endDate(sectionClassDTO.getEndDate())
				.lockStatus(sectionClassDTO.getLockStatus())
				.maxStudent(sectionClassDTO.getMaxStudent())
				.sectionClassNamePresent(sectionClassDTO.getSectionClassNamePresent())
				.startDate(sectionClassDTO.getStartDate())
				.subject(subjectRepository.findById(sectionClassDTO.getSubjectId()).orElse(null))
				.teacher(teacherRepository.findById(sectionClassDTO.getTeacherId()).orElse(null))
				.semesterSchool(semesterSchoolRepository.findById(sectionClassDTO.getSemesterSchoolId()).orElse(null))
				.sectionClassType(sectionClassDTO.getSectionClassType())
				.createdAt(new Date())
				.build();
		SectionClass s = sectionClassRepository.save(sectionClass);
		
		// Sinh ra các groups từ LHP
		int groups = sectionClassDTO.getGroups();
		int maxStudent = sectionClassDTO.getMaxStudent();
		if(groups > 0) {
			for (int i = 1; i <= groups; i++) {
				String classGroupName = "NHOM_" + i;
				int remainder = maxStudent % groups; // 1
				int maxStudentForGroup = (maxStudent / groups) + remainder;// 26
				SectionClassGroup sectionClassGroup = SectionClassGroup
						.builder()
						.classGroupName(ClassGroupName.valueOf(classGroupName))
						.maxStudent(maxStudentForGroup)
						.sectionClass(s)
						.build();
				sectionClassGroupRepository.save(sectionClassGroup);
			}
		} else {
			SectionClassGroup sectionClassGroup = SectionClassGroup
					.builder()
					.classGroupName(ClassGroupName.valueOf("KHONG_PHAN_NHOM"))
					.maxStudent(maxStudent)
					.sectionClass(s)
					.build();
			sectionClassGroupRepository.save(sectionClassGroup);
		}
		return ResponseEntity.ok(s);
	}
	
	@PostMapping("/timeTable/save")
	public ResponseEntity<?> saveTimeTable(@RequestBody TimeTableDTO timeTableDTO) {
		SectionClassGroup sectionClassGroup = sectionClassGroupRepository.findById(timeTableDTO.getSectionClassGroupId()).orElse(null);
		SectionClass sectionClass = sectionClassGroup.getSectionClass();
		Subject subject = sectionClass.getSubject();
		if(!timeTableDTO.isAutoGenerated()) {// Nếu ko check isAutoGenerated
			TimeTable timeTable = TimeTable
					.builder()
					.startLessionTime(timeTableDTO.getStartLessionTime())
					.endLessionTime(timeTableDTO.getEndLessionTime())
					.lessionType(timeTableDTO.getLessionType())
					.lessionDay(timeTableDTO.getLessionDay())
					.lessionDate(timeTableDTO.getLessionDate())
					.room(timeTableDTO.getRoom())
					.note(timeTableDTO.getNote())
					.classGroupName(null)
					.sectionClassGroup(sectionClassGroupRepository.findById(timeTableDTO.getSectionClassGroupId()).orElse(null))
					.build();
			TimeTable t = timeTableRepository.save(timeTable);
			System.out.println("timeTable=" + t);
			return ResponseEntity.ok()
					.contentType(MediaType.TEXT_PLAIN)
					.body("OK");
		}
		
		// TH isAutoGenerated = true
		
		
		// Môn 3 chỉ -> 10 tuần lịch LT, 10 Tuần lịch TH
		Date lessionDate = timeTableDTO.getLessionDate();
		// Render lịch LT
		switch (timeTableDTO.getLessionType()) {
			case LessionType.LY_THUYET: {
				int numberOfTheory = subject.getNumberOfTheory();
				int loopCountTheory = numberOfTheory / 3;
				for(int i = 0; i<loopCountTheory; i++) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(lessionDate);
					calendar.add(Calendar.DAY_OF_MONTH, 7 * i);
					Date nextDate = calendar.getTime();
					TimeTable timeTable = TimeTable
							.builder()
							.startLessionTime(timeTableDTO.getStartLessionTime())
							.endLessionTime(timeTableDTO.getEndLessionTime())
							.lessionType(timeTableDTO.getLessionType())
							.lessionDay(timeTableDTO.getLessionDay())
							.lessionDate(nextDate)
							.room(timeTableDTO.getRoom())
							.note(timeTableDTO.getNote())
							.classGroupName(sectionClassGroup.getClassGroupName())
							.sectionClassGroup(sectionClassGroupRepository.findById(timeTableDTO.getSectionClassGroupId()).orElse(null))
							.teacher(sectionClass.getTeacher())
							.build();
					timeTableRepository.save(timeTable);
				}
					
				return ResponseEntity.ok()
						.contentType(MediaType.TEXT_PLAIN)
						.body("OK");
			}
			case LessionType.THUC_HANH:
				int numberOfExcerise = subject.getNumberOfExcerise();
				int loopCountExcerise = numberOfExcerise / 3;
				for(int i = 0; i<loopCountExcerise; i++) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(lessionDate);
					calendar.add(Calendar.DAY_OF_MONTH, 7 * (i+2));
					Date nextDate = calendar.getTime();
					System.out.println("nextDate2===========>>>>" + nextDate);
					TimeTable timeTable = TimeTable
							.builder()
							.startLessionTime(timeTableDTO.getStartLessionTime())
							.endLessionTime(timeTableDTO.getEndLessionTime())
							.lessionType(timeTableDTO.getLessionType())
							.lessionDay(timeTableDTO.getLessionDay())
							.lessionDate(nextDate)
							.room(timeTableDTO.getRoom())
							.note(timeTableDTO.getNote())
							.classGroupName(sectionClassGroup.getClassGroupName())
							.sectionClassGroup(sectionClassGroupRepository.findById(timeTableDTO.getSectionClassGroupId()).orElse(null))
							.teacher(sectionClass.getTeacher())
							.build();
					timeTableRepository.save(timeTable);
				}
				return ResponseEntity.ok()
						.contentType(MediaType.TEXT_PLAIN)
						.body("OK");
			default:
				throw new IllegalArgumentException("Can't auto generated many time tables for Unexpected value: " + timeTableDTO.getLessionType());
		}
	}
}
