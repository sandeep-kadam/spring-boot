package com.sk.springboot.service;

import java.util.List;

import com.sk.springboot.entity.Employee;

public interface IEmployeeService {

	public List<Employee> findAll();
	public void save(Employee employee);
	public Employee findById(int employeeId);
	public void deleteById(int employeeId);
	
	/*public int checkEmployeeExistsOrNot(String email);*/
}
