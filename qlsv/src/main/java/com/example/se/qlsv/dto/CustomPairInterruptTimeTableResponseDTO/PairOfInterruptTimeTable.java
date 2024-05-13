package com.example.se.qlsv.dto.CustomPairInterruptTimeTableResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PairOfInterruptTimeTable {
	private CustomObjectSectionClassTimeTable studentConflictObject;
	private CustomObjectSectionClassTimeTable sectionClassConflictObject;
}
