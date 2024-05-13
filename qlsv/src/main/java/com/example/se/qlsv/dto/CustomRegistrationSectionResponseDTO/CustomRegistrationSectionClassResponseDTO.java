package com.example.se.qlsv.dto.CustomRegistrationSectionResponseDTO;

import com.example.se.qlsv.entity.RegistrationSectionClass;
import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.SemesterSchool;
import com.example.se.qlsv.entity.Subject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomRegistrationSectionClassResponseDTO {
	private SectionClass sectionClass;
	private RegistrationSectionClass registrationSectionClass;
	private Subject subject;
	private SemesterSchool semesterSchool;
}
