package com.example.se.qlsv.dto.CustomSemesterPatternResponseDTO;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SemesterPatternRequiredCredits {
	private long semesterPatternId;
	private String stageSemester;
	private int totalRequiredCredits;
	private int totalAccquiredCredits;
	private int totalOptionalCredits;
	private List<CustomMySubjectIsPass> accquiredSubjects;
	private List<CustomMySubjectIsPass> optionalSubjects;
}
