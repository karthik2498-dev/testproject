package com.test.pro.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity(name="Address")
@Component
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long address_id;
	
	
	private String address;
	
	@OneToOne(mappedBy="Address",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Profiles profile;


	public Profiles getProfile() {
		return profile;
	}


	public void setProfile(Profiles profile) {
		this.profile = profile;
	}


	public Long getAddress_id() {
		return address_id;
	}


	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
}
