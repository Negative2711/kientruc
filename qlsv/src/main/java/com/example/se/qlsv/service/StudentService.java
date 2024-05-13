package com.example.se.qlsv.service;

import com.example.se.qlsv.entity.Student;

public interface StudentService {
	public Student getStudentById(long studentId);
	public Student findByUserId(long userId);
}
