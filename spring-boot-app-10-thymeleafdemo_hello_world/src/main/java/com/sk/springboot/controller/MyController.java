package com.sk.springboot.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

	
	@GetMapping("/helloworld")
	public String getMessage(Model model) {
		
		model.addAttribute("myDate", new Date());
		return "helloworld";
	}
}
