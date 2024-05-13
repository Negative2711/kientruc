//package com.example.se.qlsv.repository;
//
//import org.springframework.stereotype.Service;
//
//import com.example.se.qlsv.entity.Student;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//@Service
//public class StudentRepositiryImpl implements StudentRepository {
//	
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Override
//	public Student getStudentById(long studentId) {
//		Student studentData = (Student) entityManager.createNamedQuery("Student.getStudentById")
//			.setParameter("studentId", studentId)
//			.getSingleResult();
//		System.out.println("studentDataDepartment=========="+studentData.getDepartment().getStudents());
//		System.out.println("Hello World: " + studentId);
//		return null;
//	}
//
//}
