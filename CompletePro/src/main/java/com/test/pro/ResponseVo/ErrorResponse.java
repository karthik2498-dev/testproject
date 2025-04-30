package com.test.pro.ResponseVo;

public class ErrorResponse {

	private String ErrorMessage;

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public ErrorResponse(String errorMessage) {
		this.ErrorMessage = errorMessage;
	}

	public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	
	
}
