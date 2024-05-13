package com.example.se.qlsv.dto.CustomPairInterruptTimeTableResponseDTO;

import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.Subject;
import com.example.se.qlsv.entity.TimeTable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomObjectSectionClassTimeTable {
	private SectionClass sectionClass;
	private Subject subject;
	private TimeTable timeTable;
}
