package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.example.se.qlsv.enumric.SectionClassStatus;
import com.example.se.qlsv.enumric.SectionClassType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class SectionClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String sectionClassNamePresent;
	private int maxStudent;
	private Date startDate;
	private Date endDate;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	@Enumerated(EnumType.STRING)
	private SectionClassStatus lockStatus;
	@Enumerated(EnumType.STRING)
	private SectionClassType sectionClassType;
	private int registeredNumber;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "sectionClass")
	@JsonManagedReference
	@JsonIgnore
	private Set<SectionClassGroup> sectionClassGroups;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "sectionClass")
	@JsonManagedReference
	@JsonIgnore
	private Set<RegistrationSectionClass> registrationSectionClasses; // ✔️
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	@JsonBackReference
	private Teacher teacher; // ✔️
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "subject_id")
	private Subject subject; // ✔️
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "semester_school_id")
	private SemesterSchool semesterSchool; // ko có thuộc tính mapped 2 chiều từ semesterSchool oneToMany tới SectionClass vì lấy ra list SectionClass từ semesterSchool là điều không cần thiết
	
	public void updateRegisteredStudent() {
	    this.registeredNumber = this.registrationSectionClasses.size();
	}
}
