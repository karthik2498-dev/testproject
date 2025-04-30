package com.test.pro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.pro.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long>{

	@Query("SELECT d FROM Department d JOIN FETCH d.Profiles WHERE d.department_id = :id")
    Department findByIdWithProfiles(@Param("id") Long id);
	
	
	@Query("SELECT d FROM Department d JOIN FETCH d.Profiles ORDER BY d.department_id DESC")
    List<Department> findAllProfiles();
	
}
