package com.sk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@SpringBootApplication(scanBasePackages={"com.sk.springboot"})*/
public class SpringBootAppDemo {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppDemo.class, args);
	}

}
