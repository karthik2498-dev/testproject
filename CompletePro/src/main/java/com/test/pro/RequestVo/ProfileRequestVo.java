package com.test.pro.RequestVo;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Component
public class ProfileRequestVo {
		
	
		@NotNull(message="ProfileId should not be Null")
		private Long profileId;

		@NotNull(message="Name should not be Null")
		private String name;
		
		@Max(50)
		@Min(18)
		private int age;
		
		private Long department_id;
		
		@NotNull(message="Address should not be Null")
		private String address;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Long getDepartment_id() {
			return department_id;
		}

		public void setDepartment_id(Long department_id) {
			this.department_id = department_id;
		}

		public Long getProfileId() {
			return profileId;
		}

		public void setProfileId(Long profileId) {
			this.profileId = profileId;
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
	
}
