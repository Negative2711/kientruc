package com.example.se.qlsv.dto.CustomStudentGradeReponseDTO;

import java.util.List;

import com.example.se.qlsv.entity.SemesterPattern;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomSemesterStudentGrades {
	private SemesterPattern semesterPattern;
	private List<CustomStudentGradeReponseDTO> studentGrades;
}
