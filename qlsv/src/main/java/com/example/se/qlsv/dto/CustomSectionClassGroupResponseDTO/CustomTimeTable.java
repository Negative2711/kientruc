package com.example.se.qlsv.dto.CustomSectionClassGroupResponseDTO;

import com.example.se.qlsv.entity.Teacher;
import com.example.se.qlsv.entity.TimeTable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomTimeTable {
	private TimeTable timeTable;
	private Teacher teacher;
}
