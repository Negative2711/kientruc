package com.example.se.qlsv.dto.CustomStudentProgressResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomStudentProgressResponseDTO {
	private int currentCredits;
	private int requiredCredits;
}
