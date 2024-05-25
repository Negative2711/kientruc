package com.example.se.qlsv.dto.CustomRegistrationSectionResponseDTO;

import java.util.Set;

import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.SectionClassGroup;
import com.example.se.qlsv.entity.Teacher;
import com.example.se.qlsv.entity.TimeTable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomSpectorRegistrationSectionClassResponseDTO {
	private SectionClass sectionClass;
	private Teacher teacher;
	private SectionClassGroup sectionClassGroup;
	private Set<TimeTable> timeTables;
}
