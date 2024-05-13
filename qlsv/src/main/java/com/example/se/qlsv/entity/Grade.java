package com.example.se.qlsv.entity;

import java.io.Serializable;

import com.example.se.qlsv.enumric.GradeRanking;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Grade implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private float gradeLT1;
	private float gradeLT2;
	private float gradeLT3;
	private float gradeTH1;
	private float gradeTH2;
	private float gradeTH3;
	
	private float gradeGK;
	private float gradeCK;
	
	private float finalGrade;
	private String letterGrade;
	@Enumerated(EnumType.STRING)
	private GradeRanking gradeRanking;
	
	private String note;
	private boolean isPass;
	
	@OneToOne
	@JoinColumn( name = "registration_section_class_id" )
	@JsonIgnore
	private RegistrationSectionClass registrationSectionClass;
}
