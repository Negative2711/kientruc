package com.example.se.qlsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	@Query(value = "SELECT DISTINCT s.* FROM subject s JOIN section_class sc ON s.id = sc.subject_id WHERE sc.semester_school_id = ?1 AND s.major_id = ?2 ORDER BY sc.id DESC", nativeQuery = true)
	public List<Subject> getSubjectBySemesterAndStudentMajor(long semesterId, long studentMajorId);
	
	@Query(value = "SELECT s.* FROM registration_section_class rsc JOIN grade g ON g.registration_section_class_id = rsc.id JOIN section_class sc ON sc.id = rsc.section_class_id JOIN subject s on s.id = sc.subject_id WHERE rsc.student_id = ?1 AND g.is_pass = 1", nativeQuery = true)
	public List<Subject> getSubjectStudentPassed(long studentId);

	@Query(value = "SELECT * FROM subject WHERE semester_pattern_id = ?1 AND major_id = ?2 AND subject_type = 'BAT_BUOC'", nativeQuery = true)
	public List<Subject> getAccquiredSubjectsBySemesterPatternAndMajor(long semesterPatternId, long majorId);
	
	@Query(value = "SELECT * FROM subject WHERE semester_pattern_id = ?1 AND major_id = ?2 AND subject_type = 'TU_CHON'", nativeQuery = true)
	public List<Subject> getOptionalSubjectsBySemesterPatternAndMajor(long semesterPatternId, long majorId);
	
	@Query(value = "SELECT COALESCE(SUM(credits), 0) FROM subject WHERE semester_pattern_id = ?1 AND major_id = ?2", nativeQuery = true)
	public int getTotalCreditsBySemesterPatternAndMajor(long semesterPatternId, long majorId);
	
	@Query(value = "SELECT COALESCE(SUM(credits), 0) FROM subject WHERE semester_pattern_id = ?1 AND major_id = ?2 AND subject_type = 'BAT_BUOC'", nativeQuery = true)
	public int getAccquiredCreditsBySemesterPatternAndMajor(long semesterPatternId, long majorId);
	
	@Query(value = "SELECT COALESCE(SUM(credits), 0) FROM subject WHERE semester_pattern_id = ?1 AND major_id = ?2 AND subject_type = 'TU_CHON'", nativeQuery = true)
	public int getOptionalCreditsBySemesterPatternAndMajor(long semesterPatternId, long majorId);
}
