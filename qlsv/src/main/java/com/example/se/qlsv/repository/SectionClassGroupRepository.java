package com.example.se.qlsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.SectionClassGroup;

@Repository
public interface SectionClassGroupRepository extends JpaRepository<SectionClassGroup, Long> {
	@Query(value = "SELECT * FROM section_class_group WHERE section_class_id = ?1", nativeQuery = true)
	public List<SectionClassGroup> getSectionClassGroupBySectionClass(long sectionClassId);
	;
}
