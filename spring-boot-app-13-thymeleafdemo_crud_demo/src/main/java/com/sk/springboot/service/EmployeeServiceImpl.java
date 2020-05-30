package com.sk.springboot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.springboot.dao.IEmployeeRepository;
import com.sk.springboot.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository; 
	
	@Override
	@Transactional 
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	@Transactional 
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public Employee findById(int employeeId) {
		Optional<Employee> result = employeeRepository.findById(employeeId);
		Employee employee = null;
		
		if(result.isPresent()) {
			employee = result.get();
		} else {
			throw new RuntimeException("Employee not found with employeeId : "+employeeId);
		}
		
		return employee;
	}

	@Override
	public void deleteById(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}
	
	/*@Override
	public int checkEmployeeExistsOrNot(String email) {
		List<Employee> result = employeeRepository.findByEmail(email);
		Employee employee = null;
		int employeeId = 0;
		if(result.size() == 1) {
			employee = result.get(0);
			employeeId = employee.getId();
		} else if(result.size() > 1) {
			throw new RuntimeException("More than one record exists #");
		}
		
		System.out.println("EmployeeId ### "+employeeId);
		
		return employeeId;
	}*/

}
