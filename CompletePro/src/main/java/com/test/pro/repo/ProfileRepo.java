package com.test.pro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.pro.model.Profiles;

@Repository
public interface ProfileRepo extends JpaRepository<Profiles,Long> {

	
	
}
