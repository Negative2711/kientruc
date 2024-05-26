package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.annotations.Comment;

import com.example.se.qlsv.enumric.ClassGroupName;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class SectionClassGroup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
	@Comment("Da co san trong section_class_group_id nhung van ton tai cho de hinh dung")
	private ClassGroupName classGroupName;
	private int maxStudent;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "sectionClassGroup")
	@JsonManagedReference
	@JsonIgnore
	private Set<TimeTable> timeTables;
	
	@ManyToOne()
	@JoinColumn(name = "section_class_id")
	@JsonBackReference
	private SectionClass sectionClass;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "sectionClassGroup")
	@JsonManagedReference
	@JsonIgnore
	private Set<RegistrationSectionClass> registrationSectionClasses; // ✔️
}
