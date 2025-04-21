package com.test.pro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.pro.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long>{

}
