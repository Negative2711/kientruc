package com.example.se.qlsv.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
	@Query(value = "SELECT tb.* FROM registration_section_class rsc JOIN section_class sc ON rsc.section_class_id = sc.id JOIN time_table tb ON tb.section_class_group_id = rsc.section_class_group_id WHERE rsc.student_id = ?1 and sc.semester_school_id = ?2", nativeQuery = true)
	public List<TimeTable> getStudentTimeTables(long studentId, long semesterId);
	
	@Query(value = "SELECT tb.* FROM time_table tb\r\n"
			+ "JOIN section_class_group scg ON scg.id = tb.section_class_group_id\r\n"
			+ "JOIN section_class sc ON sc.id = scg.section_class_id\r\n"
			+ "WHERE sc.id = ?1", nativeQuery = true)
	public List<TimeTable> getTimeTablesBySectionClass(long sectionClassId);
	
	@Query(value = "SELECT * FROM time_table WHERE section_class_group_id = ?1", nativeQuery = true)
	public List<TimeTable> getTimeTablesBySectionClassGroup(long sectionClassGroupId);

	@Query(value = "SELECT tb.* FROM time_table tb JOIN section_class_group scg ON scg.id = tb.section_class_group_id JOIN registration_section_class rsc ON rsc.section_class_group_id = scg.id WHERE rsc.student_id = :studentId AND tb.lession_date BETWEEN DATE(:mondayDate) AND DATE(:sundayDate)", nativeQuery = true)
	public List<TimeTable> getTimeTablesByMonAndSun(@Param("studentId") long studentId, @Param("mondayDate") Date mondayDate, @Param("sundayDate") Date sundayDate);
	
	@Query(value = "SELECT tb.* FROM time_table tb JOIN section_class_group scg ON scg.id = tb.section_class_group_id JOIN registration_section_class rsc ON rsc.section_class_group_id = scg.id WHERE rsc.student_id = :studentId AND tb.lession_date BETWEEN DATE(:mondayDate) AND DATE(:sundayDate) AND tb.lession_type = 'THI'", nativeQuery = true)
	public List<TimeTable> getTimeTablesByMonAndSunFilterExaming(@Param("studentId") long studentId, @Param("mondayDate") Date mondayDate, @Param("sundayDate") Date sundayDate);
	
	@Query(value = "SELECT tb.* FROM time_table tb JOIN section_class_group scg ON scg.id = tb.section_class_group_id JOIN registration_section_class rsc ON rsc.section_class_group_id = scg.id WHERE rsc.student_id = :studentId AND tb.lession_date BETWEEN DATE(:mondayDate) AND DATE(:sundayDate) AND tb.lession_type != 'THI'", nativeQuery = true)
	public List<TimeTable> getTimeTablesByMonAndSunFilterNotExaming(@Param("studentId") long studentId, @Param("mondayDate") Date mondayDate, @Param("sundayDate") Date sundayDate);
}
