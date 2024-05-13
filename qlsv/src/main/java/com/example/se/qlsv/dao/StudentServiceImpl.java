package com.example.se.qlsv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.se.qlsv.entity.Student;
import com.example.se.qlsv.repository.StudentRepository;
import com.example.se.qlsv.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student getStudentById(long studentId) {
		return studentRepository.findById(studentId);
	}

	@Override
	public Student findByUserId(long userId) {
		return studentRepository.findByUserId(userId);
	}

}
