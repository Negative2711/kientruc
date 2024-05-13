package com.example.se.qlsv.dto.CustomRegistrationSectionResponseDTO;

import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.SectionClassGroup;
import com.example.se.qlsv.entity.Teacher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomSpectorRegistrationSectionClassResponseDTO {
	private SectionClass sectionClass;
	private Teacher teacher;
	private SectionClassGroup sectionClassGroup;
}
