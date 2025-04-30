package com.test.pro.ResponseVo;

import java.util.List;

import com.test.pro.model.Department;

public class DepartmentResponse {
	
	
	private List<Department> dep;
	
	private String message;

	public List<Department> getDep() {
		return dep;
	}

	public void setDep(List<Department> dep) {
		this.dep = dep;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
