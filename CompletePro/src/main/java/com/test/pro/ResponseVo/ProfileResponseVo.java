package com.test.pro.ResponseVo;

import org.springframework.stereotype.Component;


@Component
public class ProfileResponseVo {
			
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

			public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

			private Long profileId;

			private String Name;
			
			private int Age;
			
			private String message;
		
}
