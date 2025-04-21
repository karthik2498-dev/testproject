package com.test.pro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.test.pro.ResponseVo.ErrorResponse;

@RestControllerAdvice
public class GlobleExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> MethodNotFoundException(MethodArgumentNotValidException exp){
		
		ErrorResponse response = new ErrorResponse();
		
	    exp.getBindingResult().getAllErrors().forEach(error-> {
			String str= ((FieldError)error).getField();
			response.setErrorMessage(str+" : "+error.getDefaultMessage());
		});
		
	    
	    
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}
	
}
