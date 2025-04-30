package com.test.pro.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="Department")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "department_id")
public class Department{
	
	@Id
	private Long department_id;
	
	public Long getDepartment_id() {
		return department_id;
	}


	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}


	public String getDepartment_name() {
		return Department_name;
	}


	public void setDepartment_name(String department_name) {
		Department_name = department_name;
	}


	public List<Profiles> getProfile() {
		return Profiles;
	}


	public void setProfile(List<Profiles> profile) {
		this.Profiles = profile;
	}


	private String Department_name;
	
	
	@OneToMany(mappedBy="Department" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Profiles> Profiles;

}
