package com.example.se.qlsv.dto;

import java.util.Date;

import com.example.se.qlsv.enumric.SectionClassStatus;
import com.example.se.qlsv.enumric.SectionClassType;

import lombok.Data;

@Data
public class SectionClassDTO {
	private String sectionClassNamePresent;
	private int maxStudent;
	private Date startDate;
	private Date endDate;
	private long subjectId;
	private long teacherId;
	private long semesterSchoolId;
	private int groups;
	private SectionClassType sectionClassType;
	private SectionClassStatus lockStatus;
}
