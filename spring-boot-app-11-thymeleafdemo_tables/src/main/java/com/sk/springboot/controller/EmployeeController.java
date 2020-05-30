package com.sk.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sk.springboot.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private List<Employee> employeeList = new ArrayList<>();
	
	
	@PostConstruct
	private void loadData() {
		System.out.println("inside loadData() ###");
		Employee emp1 = new Employee(1,"sandeep", "kadam", "sandeepk@gmail.com");
		Employee emp2 = new Employee(2,"john", "bosco", "john@gmail.com");
		Employee emp3 = new Employee(3,"william", "rods", "william@gmail.com");
		
		employeeList.add(emp1);
		employeeList.add(emp2);
		employeeList.add(emp3);
	}
	
	@GetMapping("/getemployees")
	public String getEmployess(Model model) {
		
		model.addAttribute("employeesList", employeeList);
		
		return "displayemployees";
	}
}
