package com.example.se.qlsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.SemesterSchool;

@Repository
public interface SemesterSchoolRepository extends JpaRepository<SemesterSchool, Long> {

}