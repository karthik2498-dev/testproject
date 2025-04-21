package com.test.pro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.pro.RequestVo.ProfileRequestVo;
import com.test.pro.ResponseVo.AddressDTO;
import com.test.pro.ResponseVo.DepartmentDTO;
import com.test.pro.ResponseVo.DepartmentResponse;
import com.test.pro.ResponseVo.ProfileDTO;
import com.test.pro.ResponseVo.ProfileGetAllResponse;
import com.test.pro.ResponseVo.ProfileResponseVo;
import com.test.pro.ResponseVo.TokenResponse;
import com.test.pro.model.Address;
import com.test.pro.model.Department;
import com.test.pro.model.Profiles;
import com.test.pro.repo.AddressRepo;
import com.test.pro.repo.DepartmentRepo;
import com.test.pro.repo.ProfileRepo;

@Service
public class ProfileService {
	
	@Autowired
	private Profiles profile;
	
	@Autowired
	private ProfileRepo profilerepo;
	
	@Autowired
	private DepartmentRepo deprepo;
	
	@Autowired
	private AddressRepo addrepo;
	
	//@Autowired
	//private ProfileResponseVo response;
	
	 @Value("${okta.oauth2.client-id}")
	    private String clientId;

	    @Value("${okta.oauth2.client-secret}")
	    private String clientSecret;

	    @Value("${okta.oauth2.token-url}")
	    private String tokenUrl;

	    @Value("${okta.oauth2.grant-type}")
	    private String grantType;

	    @Value("${okta.oauth2.scope}")
	    private String scope;
	
	
	public ProfileResponseVo SaveUser(ProfileRequestVo req) {
		
		ProfileResponseVo res = new ProfileResponseVo();
		
		Address add = new Address();
		add.setAddress(req.getAddress());
		
		System.out.println("Address :"+add.getAddress());
		System.out.println("Address2 :"+req.getAddress());
		
		addrepo.save(add);
		
		Optional<Department> dep=deprepo.findById(req.getDepartment_id());
		
		Optional<Profiles> profiles=profilerepo.findById(req.getProfileId());
		
		if(profiles.isEmpty() && !dep.isEmpty()) {
		profile.setAge(req.getAge());
		profile.setName(req.getName());
		profile.setProfileId(req.getProfileId());
		profile.setDepartment(dep.get());
		profile.setAddresss(add);

		profilerepo.save(profile);
		res.setAge(req.getAge());
		res.setName(req.getName());
		res.setProfileId(req.getProfileId());
		res.setMessage("User SuccessFully Added :");
		}
		else {
			res.setAge(req.getAge());
			res.setName(req.getName());
			res.setProfileId(req.getProfileId());
			res.setMessage("User Already Present in the DB");
		}
		
		return res;
	}
	
	
	public ProfileResponseVo UpdateUser(ProfileRequestVo req) {
		
		ProfileResponseVo response = new ProfileResponseVo();
		Optional<Profiles> profiles=profilerepo.findById(req.getProfileId());
		
		if(!profiles.isEmpty()) {
		profile.setAge(req.getAge());
		profile.setName(req.getName());
		profile.setProfileId(req.getProfileId());

		profilerepo.save(profile);
		response.setAge(req.getAge());
		response.setName(req.getName());
		response.setProfileId(req.getProfileId());
		response.setMessage("User SuccessFully Updated");
		}
		else {
			response.setAge(req.getAge());
			response.setName(req.getName());
			response.setProfileId(req.getProfileId());
			response.setMessage("User Not Present in the DB. Please use Save API to save the user");
		}
		return response;
		
		
	}
	
	public ProfileResponseVo GetUserById(Long Id) {

		ProfileResponseVo response = new ProfileResponseVo();
		Optional<Profiles> profile=profilerepo.findById(Id);
		
		if(!profile.isEmpty()) {
		response.setAge(profile.get().getAge());
		response.setName(profile.get().getName());
		response.setProfileId(profile.get().getProfileId());
		response.setMessage("User SuccessFully Retrived");
		}
		else {
			response.setProfileId(Id);
			response.setMessage("User Not present in the DB");
		}
		return response;
		
	}
	
	
	@Cacheable(value="UserCache",key="'usergetall'")
	public ProfileGetAllResponse GetAllUser() {

        List<Profiles> profiles = profilerepo.findAll();

        List<Address> add=addrepo.findAll();
        
        System.out.println("Result :" +profiles.get(0).getAddresss().getAddress());
        
        System.out.println("Result :" +add.get(0).getAddress());
        // Map Profiles entities to DTOs
        List<ProfileDTO> profileDTOs = profiles.stream()
            .map(profile -> new ProfileDTO(
                profile.getProfileId(),
                profile.getName(),
                profile.getAge(),
                new DepartmentDTO(
                    profile.getDepartment().getDepartment_id(),
                    profile.getDepartment().getDepartment_name()
                ),
                new AddressDTO(
                		profile.getAddresss().getAddress_id(),
                		profile.getAddresss().getAddress()
                		)
            ))
            .collect(Collectors.toList());

        ProfileGetAllResponse response = new ProfileGetAllResponse();
        response.setProfiles(profileDTOs);
        response.setMessage("Successfully retrieved all profiles");
        return response;
		
	}
	
	
	public ProfileResponseVo DeleteUserById(Long Id) {

		ProfileResponseVo response = new ProfileResponseVo();
		Optional<Profiles> profile=profilerepo.findById(Id);
		
		System.out.println("Profile ID :"+profile.get().getProfileId());
		
		if(profile.get().getProfileId()==Id){
		profilerepo.deleteById(Id);
		response.setAge(profile.get().getAge());
		response.setName(profile.get().getName());
		response.setProfileId(profile.get().getProfileId());
		response.setMessage("User SuccessFully Deleted");
		}
		else {
			response.setAge(profile.get().getAge());
			response.setName(profile.get().getName());
			response.setProfileId(profile.get().getProfileId());
			response.setMessage("User Not Present in the DB");
		}
		
		return response;
		
	}
	
	public String DeleteAll() {

		
		profilerepo.deleteAll();
		return "ALL the Users Are Deleted";
		
	}
	
	public String tokenGenerator() {
		
		RestTemplate rest = new RestTemplate();
		
		HttpHeaders http = new HttpHeaders();
		http.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		
		String Auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
		
		http.set("Authorization", "Basic " + Auth);
		
		// Prepare body
        String body = "grant_type=" + grantType + "&scope=" + scope;
		
        HttpEntity<String> entity = new HttpEntity<>(body, http);
		
        ResponseEntity<TokenResponse> response = rest.exchange(tokenUrl, HttpMethod.POST, entity, TokenResponse.class);
        System.out.println("TOKEN SUCCESSFULLY CREATED");
        
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getAccessToken();
        } else {
            throw new RuntimeException("Failed to fetch access token");
        }
	}	
	
}
