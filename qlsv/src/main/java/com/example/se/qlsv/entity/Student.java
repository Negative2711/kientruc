package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@NamedNativeQueries({
	@NamedNativeQuery(name = "Student.getStudentById", query = "SELECT * FROM student WHERE id = :studentId", resultClass = Student.class),
})
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	private String address;
    private String phoneNumber;
    private String email;
    private String citizenCard;
    private String gender;
    private String avatar;
    private Date dateOfBirth;
    private String placeBorn;
    private Date joinSchoolDate;
    private double yearOfStudy;
    private int yearStart;
    private int yearEnd;
    private String quanlityOfTraning;
    private String typeOfTraning;
    private String statusOfTraning;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "prime_class_id")
    @JsonBackReference
    private PrimeClass primeClass;
    
    @ManyToOne
    @JoinColumn(name = "major_id")
    @JsonBackReference
    private Major major;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    @JsonIgnore
    private Set<RegistrationSectionClass> registrationSectionClasses; // ✔️
}
