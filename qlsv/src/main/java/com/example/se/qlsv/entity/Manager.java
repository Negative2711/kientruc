package com.example.se.qlsv.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
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
public class Manager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String fullName;
	private String address;
    private String phoneNumber;
    private String email;
    private String gender;
    private String avatar;
    private Date dateOfBirth;
    private String placeBorn;
    private Date joinSchoolDate;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user; // ✔️
    
    @OneToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department; // ✔️
}
