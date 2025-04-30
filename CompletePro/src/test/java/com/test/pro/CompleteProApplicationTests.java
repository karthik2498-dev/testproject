package com.test.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.test.pro.RequestVo.ProfileRequestVo;
import com.test.pro.ResponseVo.ProfileGetAllResponse;
import com.test.pro.ResponseVo.ProfileResponseVo;
import com.test.pro.model.Profiles;
import com.test.pro.repo.ProfileRepo;
import com.test.pro.service.ProfileService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CompleteProApplicationTests {

	@Mock
    private ProfileRepo profileRepo;  // Mocked ProfileRepo

    @Mock
    private Profiles profile; // Mocked Profile entity

    @InjectMocks
    private ProfileService profileService; // ProfileService with dependencies injected
    

    private static ProfileRequestVo req=null;
    
    /*@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
       
    }*/
    
    @BeforeAll
    static void request() {
    	 req = new ProfileRequestVo();
         req.setProfileId(1L);
         req.setName("John Doe");
         req.setAge(30);
    }

    @Test
    void testSaveUser_whenUserNotPresentInDB() {
    	
        when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.empty()); // Simulate user not found
        // Act
        profileService.SaveUser(req);
        // Assert
        verify(profileRepo, times(1)).save(any(Profiles.class)); // Ensure save is called
       
       
    }

    @Test
    void testSaveUser_whenUserAlreadyPresentInDB() {

        Profiles existingProfile = new Profiles();
        existingProfile.setProfileId(1L);
        existingProfile.setName("John Doe");
        existingProfile.setAge(30);

        when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.of(existingProfile)); // Simulate user found

        // Act
        ProfileResponseVo result = profileService.SaveUser(req);

        // Assert
        verify(profileRepo, times(0)).save(any(Profiles.class)); // Ensure save is NOT called
        assertEquals("User Already Present in the DB", result.getMessage());
        assertEquals(req.getAge(), result.getAge());
        assertEquals(req.getName(), result.getName());
        assertEquals(req.getProfileId(), result.getProfileId());
    }

    @Test
    void testSaveUser_whenProfileNameIsNull() {

        Profiles existingProfile = new Profiles();
        existingProfile.setProfileId(2L);
        existingProfile.setName(null); // Simulate profile with null name
        existingProfile.setAge(20);

        when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.of(existingProfile)); // Simulate user found

        // Act
        ProfileResponseVo  result = profileService.SaveUser(req);

        // Assert
        verify(profileRepo, times(1)).save(any(Profiles.class)); // Ensure save is called
        assertEquals("User SuccessFully Added", result.getMessage());
        assertEquals(req.getAge(), result.getAge());
        assertEquals(req.getName(), result.getName());
        assertEquals(req.getProfileId(), result.getProfileId());
    }

    @Test
    void testSaveUser_whenAgeOrNameIsNull() {
        req.setName(null); // Simulate invalid input

        Profiles existingProfile = new Profiles();
        existingProfile.setProfileId(3L);
        existingProfile.setName("Existing User");
        existingProfile.setAge(20);

        when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.of(existingProfile));

        // Act
        ProfileResponseVo  result = profileService.SaveUser(req);

        // Assert
        verify(profileRepo, times(0)).save(any(Profiles.class)); // Ensure save is NOT called
        assertEquals("User Already Present in the DB", result.getMessage());
        assertEquals(30, result.getAge()); // Check that age is still correctly set
        assertNull(result.getName()); // Ensure name is null
        assertEquals(1L, result.getProfileId());
    }
    
    @Test
    void updateUserTest() {
    	
    	Profiles existingProfile = new Profiles();
        existingProfile.setProfileId(1L);
        existingProfile.setName("John Doe");
        existingProfile.setAge(30);
        when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.of(existingProfile));
    	ProfileResponseVo  result=profileService.UpdateUser(req);
    	verify(profileRepo, times(1)).save(any(Profiles.class));
    	
    	assertEquals("User SuccessFully Updated", result.getMessage());
        assertEquals(30, result.getAge()); // Check that age is still correctly set
        assertNotNull(result.getName()); // Ensure name is null
        assertEquals(1L, result.getProfileId());
    }
    
    @Test
    void updateUserTestEmpty() {
    	Profiles existingProfile = new Profiles();
        existingProfile.setProfileId(1L);
        existingProfile.setName("John Doe");
        existingProfile.setAge(30);
        when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.empty());
    	ProfileResponseVo  result=profileService.UpdateUser(req);
    	verify(profileRepo, times(0)).save(any(Profiles.class));
    	
    	assertEquals("User Not Present in the DB. Please use Save API to save the user", result.getMessage());
        assertEquals(30, result.getAge()); // Check that age is still correctly set
        assertNotNull(result.getName()); // Ensure name is not null
        assertEquals(1L, result.getProfileId());
    }
    
    @Test
    void UpdateTest() {
    	
    	when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.empty());
    	ProfileResponseVo  result=profileService.GetUserById(req.getProfileId());
    	verify(profileRepo, times(1)).findById(req.getProfileId());
    	
    	assertEquals("User Not present in the DB", result.getMessage());
        assertEquals(1L, result.getProfileId());
    	
    }
    
    @Test
    void UpdateTestPosv() {
    	
    	Long id = 1L;
        Profiles mockProfile = new Profiles();
        mockProfile.setProfileId(id);
        mockProfile.setName("John Doe");
        mockProfile.setAge(30);
    	
    	when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.of(mockProfile));
    	ProfileResponseVo  result=profileService.GetUserById(req.getProfileId());
    	verify(profileRepo, times(1)).findById(req.getProfileId());
    	
    	assertEquals("User SuccessFully Retrived", result.getMessage());
        assertEquals(1L, result.getProfileId());
        assertEquals(30, result.getAge()); // Check that age is still correctly set
        assertNotNull(result.getName()); // Ensure name is not null
    	
    }
    
	@Test
    void Getallusertest() {
    	
    	Long id = 1L;
        Profiles mockProfile = new Profiles();
        mockProfile.setProfileId(id);
        mockProfile.setName("John Doe");
        mockProfile.setAge(30);
        
        List<Profiles> pro = new ArrayList<Profiles>();
        pro.add(mockProfile);
        
    	when(profileRepo.findAll()).thenReturn(pro);
    	ProfileGetAllResponse  result=profileService.GetAllUser();
    	
    	assertEquals(30,result.getProfiles().get(0).getAge());
    }
	
	@Test
	void DeleteUserByIdTest() {
		 Long id = 1L;
	        Profiles mockProfile = new Profiles();
	        mockProfile.setProfileId(id);
	        mockProfile.setName("John Doe");
	        mockProfile.setAge(30);

	        // Mock the repository behavior
	        when(profileRepo.findById(id)).thenReturn(Optional.of(mockProfile));
	        doNothing().when(profileRepo).deleteById(id);

	        // Call the service method
	        ProfileResponseVo response = profileService.DeleteUserById(id);

	        // Assertions
	        assertNotNull(response);
	        assertEquals(id, response.getProfileId());
	        assertEquals("John Doe", response.getName());
	        assertEquals(30, response.getAge());
	        assertEquals("User SuccessFully Deleted", response.getMessage());

	        // Verify interactions
	        //verify(profileRepo, times(1)).findById(id);
	        verify(profileRepo, times(1)).deleteById(id);
		
		
	}
	
	@Test
	void DeleteUserByIdNgv() {
		Profiles mockProfile = new Profiles();
	    mockProfile.setProfileId(7L);
	    mockProfile.setName("John Doe");
	    mockProfile.setAge(30);
	    
		when(profileRepo.findById(req.getProfileId())).thenReturn(Optional.of(mockProfile));
		ProfileResponseVo  result=profileService.DeleteUserById(req.getProfileId());
		verify(profileRepo,times(0)).deleteById(req.getProfileId());
		
		assertEquals("User Not Present in the DB",result.getMessage());
		
	}
	
	@Test
	void DeleteAllUserTest() {
		
		doNothing().when(profileRepo).deleteAll();
		String result=profileService.DeleteAll();
		assertEquals("ALL the Users Are Deleted",result);
		
		
	}

}
