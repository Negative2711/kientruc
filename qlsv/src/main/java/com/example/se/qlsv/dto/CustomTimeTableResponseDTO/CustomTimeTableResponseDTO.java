package com.example.se.qlsv.dto.CustomTimeTableResponseDTO;

import java.io.Serializable;

import com.example.se.qlsv.entity.SectionClass;
import com.example.se.qlsv.entity.Subject;
import com.example.se.qlsv.entity.Teacher;
import com.example.se.qlsv.entity.TimeTable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomTimeTableResponseDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 425468837996952326L;
// Lớp dto cho render timeTables của sinh viên
	private TimeTable timeTable;
	private Subject subject;
	private SectionClass sectionClass;
	private Teacher teacher;
}
