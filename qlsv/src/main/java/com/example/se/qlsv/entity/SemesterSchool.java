package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Set;

import com.example.se.qlsv.enumric.StageSemesterSchool;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class SemesterSchool implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
	private StageSemesterSchool stageSemester;
	private int year;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "semesterSchool")
	@JsonManagedReference
	@JsonIgnore
	private Set<PrimeClass> primeClasses; // ✔️
}
