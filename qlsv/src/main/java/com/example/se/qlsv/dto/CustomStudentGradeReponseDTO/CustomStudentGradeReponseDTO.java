package com.example.se.qlsv.dto.CustomStudentGradeReponseDTO;

import com.example.se.qlsv.entity.Grade;
import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.Subject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomStudentGradeReponseDTO {
	private Grade grade;
	private SectionClass sectionClass;
	private Subject subject;
}
