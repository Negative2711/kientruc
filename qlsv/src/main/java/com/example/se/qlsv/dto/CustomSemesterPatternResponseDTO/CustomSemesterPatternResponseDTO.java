package com.example.se.qlsv.dto.CustomSemesterPatternResponseDTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomSemesterPatternResponseDTO {
	private List<SemesterPatternRequiredCredits> semesters;
	private int totalAccquiredSujectCredits;
	private int totalOptionalSubjectCredits;
	private int totalRequiredCredits;
}
