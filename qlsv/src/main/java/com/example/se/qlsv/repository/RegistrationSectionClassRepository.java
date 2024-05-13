package com.example.se.qlsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.RegistrationSectionClass;

@Repository
public interface RegistrationSectionClassRepository extends JpaRepository<RegistrationSectionClass, Long> {
	@Query(value = "SELECT rsc.* FROM registration_section_class rsc JOIN section_class sc ON sc.id = rsc.section_class_id WHERE rsc.student_id = ?1 AND sc.semester_school_id = ?2", nativeQuery = true)
	public List<RegistrationSectionClass> getStudentRegistrationSectionClassesBySemester(long studentId, long semesterId);

	@Query(value = "SELECT rsc.* FROM registration_section_class rsc JOIN section_class sc ON sc.id = rsc.section_class_id WHERE rsc.student_id = ?1 AND sc.subject_id = ?2", nativeQuery = true)
	public List<RegistrationSectionClass> getStudentRegistrationSectionClassesBySubject(Long studentId, long subjectId);
	
	@Query(value = "SELECT COUNT(*) FROM registration_section_class WHERE section_class_id = ?1", nativeQuery = true)
	public int getCountStudentBySectionClassGroup(long sectionClassGroupId);
	
	@Query(value = "SELECT * FROM registration_section_class WHERE student_id = ?1 ORDER BY id DESC", nativeQuery = true)
	public List<RegistrationSectionClass> getStudentRegistrationSectionClasses(long studentId);
	
	@Query(value = "SELECT rsc.* FROM registration_section_class rsc JOIN section_class sc ON sc.id = rsc.id WHERE sc.semester_school_id = ?1", nativeQuery = true)
	public RegistrationSectionClass getStudentRegistrationSectionClassBySemester(long semesterId);
	
	@Query(value = "SELECT rsc.* FROM registration_section_class rsc JOIN grade g ON g.registration_section_class_id = rsc.id WHERE rsc.student_id = ?1 AND g.is_pass = 1", nativeQuery = true)
	public List<RegistrationSectionClass> getRegistrationSectionClassesStudentPassed(long studentId);
}
