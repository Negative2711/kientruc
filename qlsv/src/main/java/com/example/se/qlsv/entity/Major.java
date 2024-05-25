package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
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
public class Major implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String majorName;
	private int requiredCredits;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "major")
	@JsonManagedReference
	@JsonIgnore
	private Set<Student> students;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "major")
	@JsonManagedReference
	@JsonIgnore
	private Set<Subject> subjects; // ✔️
}
