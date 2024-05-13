package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Comment;

import com.example.se.qlsv.enumric.ClassGroupName;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class RegistrationSectionClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double paid;
	private Date registerDate;
	@Enumerated(EnumType.STRING)
	@Comment("Da co san trong section_class_group_id nhung van ton tai cho de hinh dung")
	private ClassGroupName classGroupName;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	@JsonBackReference
	private Student student; // ✔️
	
	@ManyToOne
	@JoinColumn(name = "section_class_id")
	@JsonBackReference
	private SectionClass sectionClass; // ✔️
	
	@ManyToOne
	@JoinColumn(name = "section_class_group_id")
	private SectionClassGroup sectionClassGroup;
}
