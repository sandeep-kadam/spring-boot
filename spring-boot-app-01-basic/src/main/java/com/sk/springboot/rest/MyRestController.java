package com.sk.springboot.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	@Value("${company.name}")
	private String companyName = "";
	
	@GetMapping("/")
	public String sayHello() {
		System.out.println("companyName ### "+companyName);
		return "Hello World! Time on server : "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a"));
	}
	
}
