package com.example.se.qlsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.se.qlsv.entity.SectionClass;



@Repository
public interface SectionClassRepository extends JpaRepository<SectionClass, Long> {
	@Query(value = "SELECT * FROM section_class WHERE subject_id = ? AND semester_school_id = ?", nativeQuery = true)
	public List<SectionClass> getSectionClassesBySubjectAndSemester(long subjectId, long semesterId);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE section_class SET registered_number = registered_number + 1 WHERE id = ?1", nativeQuery = true)
	public void updateRegisteredNumberPlus1(long sectionClassId);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE section_class SET registered_number = registered_number - 1 WHERE id = ?1", nativeQuery = true)
	public void updateRegisteredNumberMinus1(long sectionClassId);
}
