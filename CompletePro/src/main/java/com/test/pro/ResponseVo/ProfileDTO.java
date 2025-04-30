package com.test.pro.ResponseVo;

import com.test.pro.model.Address;

public class ProfileDTO {
    private Long profileId;
    private String name;
    private int age;
    private DepartmentDTO department;
    private AddressDTO address;

    public ProfileDTO(Long profileId, String name, int age, DepartmentDTO department, AddressDTO address) {
        this.profileId = profileId;
        this.name = name;
        this.age = age;
        this.department = department;
        this.address=address;
    }

    public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Long getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }
}

