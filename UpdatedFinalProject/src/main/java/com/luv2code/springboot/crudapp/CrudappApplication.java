package com.luv2code.springboot.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"security"})
public class CrudappApplication { 

	public static void main(String[] args) {
		SpringApplication.run(CrudappApplication.class, args);
	}

}
	