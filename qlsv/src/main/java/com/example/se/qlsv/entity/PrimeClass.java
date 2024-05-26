package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class PrimeClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String className;
	private int numberOfStudent;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "primeClass", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private Set<Student> students;
	
	@ManyToOne
	@JoinColumn(name = "semester_school_id")
	@JsonBackReference
	private SemesterSchool semesterSchool;// ✔️
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	@JsonBackReference
	private Teacher teacher; // ✔️
}
