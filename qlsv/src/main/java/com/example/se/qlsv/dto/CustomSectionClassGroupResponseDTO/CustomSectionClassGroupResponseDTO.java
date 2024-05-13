package com.example.se.qlsv.dto.CustomSectionClassGroupResponseDTO;

import java.util.List;

import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.SectionClassGroup;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomSectionClassGroupResponseDTO {// 1 sectionclass group
	private SectionClassGroup sectionClassGroup;
	private List<CustomTimeTable> timeTables;
	private SectionClass sectionClass;
	private int registeredNumber;
}
