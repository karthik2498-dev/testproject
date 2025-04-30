package com.test.pro.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name="Profiles")
@Component
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "profileId")
public class Profiles {

	public Profiles() {
		// TODO Auto-generated constructor stub
	}

	public Long getProfileId() {
		return profileid;
	}

	public void setProfileId(Long profileid) {
		this.profileid = profileid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Department getDepartment() {
		return Department;
	}

	public void setDepartment(Department department) {
		this.Department = department;
	}
	
	public Address getAddresss() {
		return Address;
	}

	public void setAddresss(Address adresss) {
		Address = adresss;
	}

	@Id
	@Column(name="profileid")
	private Long profileid;

	private String name;
	
	private int age;
	
	@ManyToOne
	@JoinColumn(name="Department")
	private Department Department;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="Address")
	private Address Address;
	
}
