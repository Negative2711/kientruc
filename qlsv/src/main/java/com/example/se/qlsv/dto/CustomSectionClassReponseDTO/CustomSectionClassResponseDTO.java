package com.example.se.qlsv.dto.CustomSectionClassReponseDTO;

import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.SemesterSchool;
import com.example.se.qlsv.entity.Subject;
import com.example.se.qlsv.entity.Teacher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomSectionClassResponseDTO {
	private SectionClass sectionClass;
	private Teacher teacher;
	private Subject subject;
	private SemesterSchool semesterSchool;
}