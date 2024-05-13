package com.example.se.qlsv.entity;

import java.io.Serializable;

import com.example.se.qlsv.enumric.SubjectType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String subjectName;
	private int credits;
	@Enumerated(EnumType.STRING)
	private SubjectType subjectType;
	private int numberOfTheory;
	private int numberOfExcerise;
	private int groupNumber;
	private long preRequiredSubjectId;
	
	@ManyToOne
	@JoinColumn(name = "major_id")
	@JsonBackReference
	private Major major; // ✔️
	
	@ManyToOne
	@JoinColumn(name = "semester_pattern_id")
	@JsonBackReference
	private SemesterPattern semesterPattern; // ✔️
}
