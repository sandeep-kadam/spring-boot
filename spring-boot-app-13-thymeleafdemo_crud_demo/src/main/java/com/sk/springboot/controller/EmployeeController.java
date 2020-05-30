package com.sk.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sk.springboot.entity.Employee;
import com.sk.springboot.service.IEmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;
	
	@GetMapping("/getemployees")
	public String getEmployees(Model model) {
		
		List<Employee> employeeList = service.findAll();
		model.addAttribute("employeesList", employeeList);

		return "employees/displayEmployees";
	}
	
	@GetMapping("/showAddEmployeeForm")
	public String showAddEmployeeForm(Model model) {
		
		//create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String submitAddEmployeeForm(@ModelAttribute("employee") Employee employee) {
		
		service.save(employee);
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/getemployees";
	}
	
	@GetMapping("/showUpdateEmployeeForm")
	public String showUpdateEmployeeForm(@RequestParam int employeeId, Model model) {
		
		//create model attribute to bind form data
		Employee employee = service.findById(employeeId);
		model.addAttribute("employee", employee);

		//its the path for html template no need to start with /
		return "employees/employee-form";
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam int employeeId) {
		
		service.deleteById(employeeId);
		
		//this is path for controller it should start with /
		return "redirect:/employees/getemployees";
	}
}
