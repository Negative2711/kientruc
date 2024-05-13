package com.example.se.qlsv.dto.CustomSemesterPatternResponseDTO;

import com.example.se.qlsv.entity.Subject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomMySubjectIsPass {
	private Subject subject;
	private boolean isPass;
}
