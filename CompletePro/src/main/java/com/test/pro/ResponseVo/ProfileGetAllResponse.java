package com.test.pro.ResponseVo;

import java.util.List;

public class ProfileGetAllResponse {
	
	 private List<ProfileDTO> profiles;
	 private String message;

	    public List<ProfileDTO> getProfiles() {
	        return profiles;
	    }

	    public void setProfiles(List<ProfileDTO> profiles) {
	        this.profiles = profiles;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

}
