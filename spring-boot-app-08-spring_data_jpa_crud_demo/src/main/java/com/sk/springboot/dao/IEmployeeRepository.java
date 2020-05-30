package com.sk.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sk.springboot.entity.Employee;
														//Entity Type , Primary key data type
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	
	 @Query("select e from Employee e where e.email like %?1")
	 List<Employee> findByEmail(String email);
}
