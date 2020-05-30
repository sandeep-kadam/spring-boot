package com.sk.springboot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.springboot.entity.Employee;
import com.sk.springboot.service.IEmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private IEmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	@PostMapping("/employees/")
	public Employee addEmployee(@RequestBody Employee employee) {
		
		employee.setId(0);
		
		int employeeId = employeeService.checkEmployeeExistsOrNot(employee.getEmail());
		
		if(Integer.toString(employeeId) != null && employeeId != 0) {
			throw new RuntimeException("Employee Alread exists with employeeId : "+employeeId);
		}
		
		employeeService.save(employee);
		
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		employeeService.save(employee);
		return employee;
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {
		Employee employee = employeeService.findById(employeeId);
		
		if(employee == null) {
			throw new RuntimeException("Employee id not found - "+employeeId);
		}
		
		return employee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee employee = employeeService.findById(employeeId);
		
		if(employee==null) {
			throw new RuntimeException("Employee not found with employeedId -"+employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted employee id : "+employeeId;
	}
}
