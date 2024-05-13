package com.example.se.qlsv.entity;

import java.io.Serializable;

import com.example.se.qlsv.enumric.RoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	public Role() {
		super();
	}

	public Role(long id, RoleName roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}
}
