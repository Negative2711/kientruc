package com.example.se.qlsv.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.se.qlsv.entity.Department;
import com.example.se.qlsv.entity.Major;
import com.example.se.qlsv.entity.PrimeClass;
import com.example.se.qlsv.entity.Student;
import com.example.se.qlsv.entity.User;
import com.example.se.qlsv.service.StudentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private StudentService service;

	@GetMapping("/hello")
	public String helloWorld() {
		Student studentById = service.getStudentById(1);
		return "Hello World!";
	}
	
	@GetMapping("/student/generate")
	public String generateStudent() {
		Department departmentCNTT = Department
				.builder()
				.id(1001)
				.departmentName("KHOA CÔNG NGHỆ THÔNG TIN")
				.build();
		Major majorKTTPM = Major
				.builder()
				.id(1001)
				.majorName("Kỹ Thuật PM")
				.build();
		PrimeClass primeClass = PrimeClass
				.builder()
				.id(1)
				.build();
		User user = new User();
		user.setMssv("19524793");
		user.setPassword("123123az");
		Student student = Student
				.builder()
				.address("60/122 Tổ 10 KP8 Q12 TPHCM")
				.avatar("https://res.cloudinary.com/dopzctbyo/image/upload/v1672644566/sv_dkhp/sv-iuh-avatar-pattern_oyubmc.jpg")
				.dateOfBirth(new Date())
				.email("taito1doraemon@gmail.com")
				.fullName("Phan Tấn Tài")
				.gender("NAM")
				.joinSchoolDate(new Date(2020, 01, 01))
				.phoneNumber("0338188506")
				.placeBorn("HỒ CHÍ MINH")
				.quanlityOfTraning("CLC")
				.statusOfTraning("Đang học")
				.typeOfTraning("COLLEGE")
				.yearEnd(2025)
				.yearOfStudy(5)
				.yearStart(2020)
				.department(departmentCNTT)
				.major(majorKTTPM)
				.primeClass(primeClass)
				.statusOfTraning("Đang học")
				.build();
		return "Hello World!";
	}
	
	@GetMapping("/student")
	@PreAuthorize("hasRole('STUDENT')")
	public String studentAccess() {
		return "Student content.";
	}
	
	@GetMapping("/teacher")
	@PreAuthorize("hasRole('TEACHER')")
	public String teacherAccess() {
		return "Teacher content.";
	}
	
	@GetMapping("/manager")
	@PreAuthorize("hasRole('MANAGER')")
	public String managerAccess() {
		return "Manager content.";
	}
}