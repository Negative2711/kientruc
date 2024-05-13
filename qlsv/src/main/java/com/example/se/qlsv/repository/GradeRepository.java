package com.example.se.qlsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>{
	@Query(value = "SELECT * FROM grade WHERE registration_section_class_id = ?1 LIMIT 1", nativeQuery = true)
	public Grade getGradeByRegistrationSectionClass(long registrationSectionClassId);
	
	@Query(value = "SELECT g.* FROM grade g JOIN registration_section_class rsc ON rsc.id = g.registration_section_class_id JOIN section_class sc ON sc.id = rsc.section_class_id JOIN subject s ON s.id = sc.subject_id WHERE rsc.student_id = ?1 AND s.semester_pattern_id = ?2", nativeQuery = true)
	public List<Grade> getStudentGradesBySemesterPattern(long studentId, long semesterPatternId);
	
	@Query(value = "SELECT count(*) FROM grade g JOIN registration_section_class rsc ON g.registration_section_class_id = rsc.id JOIN section_class sc ON sc.id = rsc.section_class_id WHERE sc.subject_id = ?2 AND rsc.student_id = ?1 AND g.is_pass = 1", nativeQuery = true)
	public int getCountStudentPassPreRequiredSubject(long studentId, long preRequiredSubjectId);
	
}
