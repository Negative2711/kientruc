package com.example.se.qlsv.dto.CustomMonAndSunRequestDTO;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomMonAndSunRequestDTO {
	private long studentId;
	private Date mondayDate;
	private Date sundayDate;
	private String lessionType;
}
