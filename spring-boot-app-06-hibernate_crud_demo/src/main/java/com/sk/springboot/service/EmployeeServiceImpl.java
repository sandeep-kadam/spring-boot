package com.sk.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.springboot.dao.IEmployeeDAO;
import com.sk.springboot.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDAO employeeDAO; 
	
	@Override
	@Transactional 
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional 
	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	@Override
	@Transactional 
	public Employee findById(int employeeId) {
		return employeeDAO.findById(employeeId);
	}

	@Override
	@Transactional 
	public void deleteById(int employeeId) {
		employeeDAO.deleteById(employeeId);
	}
	
	@Override
	@Transactional
	public int checkEmployeeExistsOrNot(String email) {
		return employeeDAO.checkEmployeeExistsOrNot(email);
	}

}
