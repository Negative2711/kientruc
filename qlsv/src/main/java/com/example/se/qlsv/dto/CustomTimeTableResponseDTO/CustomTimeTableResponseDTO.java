package com.example.se.qlsv.dto.CustomTimeTableResponseDTO;

import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.Subject;
import com.example.se.qlsv.entity.Teacher;
import com.example.se.qlsv.entity.TimeTable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomTimeTableResponseDTO {// Lớp dto cho render timeTables của sinh viên
	private TimeTable timeTable;
	private Subject subject;
	private SectionClass sectionClass;
	private Teacher teacher;
}
