package com.test.pro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.pro.RequestVo.ProfileRequestVo;
import com.test.pro.ResponseVo.DepartmentResponse;
import com.test.pro.ResponseVo.ProfileGetAllResponse;
import com.test.pro.ResponseVo.ProfileResponseVo;
import com.test.pro.model.Department;
import com.test.pro.model.Profiles;
import com.test.pro.service.ProfileService;

import jakarta.validation.Valid;

@RestController
public class ProfileController {
	
	@Autowired
	private ProfileService service;

	
	@GetMapping("/GetAllUser")
	public ResponseEntity<ProfileGetAllResponse> GetAllProfiles(){
		ProfileGetAllResponse response=service.GetAllUser();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/token")
	public ResponseEntity<String> GetToken(){
		
		String response=service.tokenGenerator();
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/GetUserById/{Id}")
	public ResponseEntity<ProfileResponseVo> GetProfileById(@PathVariable("Id") Long Id){
		
		ProfileResponseVo response=service.GetUserById(Id);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/Save")
	public ResponseEntity<?> SaveProfiles(@Valid @RequestBody ProfileRequestVo requestVo){
		
		System.out.println("Age "+requestVo.getAge());
		System.out.println("Id "+requestVo.getProfileId());
		System.out.println("Name "+requestVo.getName());
		
		ProfileResponseVo response=service.SaveUser(requestVo);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/Delete/{Id}")
	public ResponseEntity<ProfileResponseVo> DeleteProfilesById(@PathVariable("Id") Long Id){
		
		ProfileResponseVo response=service.DeleteUserById(Id);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@PatchMapping("/Update")
	public ResponseEntity<ProfileResponseVo> UpdateProfile(@RequestBody ProfileRequestVo request){
		ProfileResponseVo response=service.UpdateUser(request);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/DeleteAll")
	public ResponseEntity<String> DeleteAllProfiles(){
		
		String response=service.DeleteAll();
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
}
