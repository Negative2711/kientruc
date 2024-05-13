package com.example.se.qlsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	public Student findById(long studentId);
	public Student findByUserId(long userId);
}
