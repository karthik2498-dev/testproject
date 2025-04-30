package com.test.pro.ResponseVo;

import com.test.pro.model.Profiles;

public class AddressDTO {

	
	private Long AddressId;
	
	private String Address;
	
	
	public AddressDTO(Long addressId, String Address) {
		this.AddressId=addressId;
		this.Address=Address;
	}

	public Long getAdressId() {
		return AddressId;
	}

	public void setAdressId(Long addressId) {
		AddressId = addressId;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
}
