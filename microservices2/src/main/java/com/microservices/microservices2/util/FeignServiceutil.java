package com.microservices.microservices2.util;


import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.microservices2.entity.Student;

import SecurityConfig.FeignConfig;



@FeignClient(value="feignDemo",url = "http://localhost:8080/api", configuration = FeignConfig.class)
public interface FeignServiceutil {
	
	@GetMapping("/students")
	String getName();
	
	@GetMapping("/students/{studentId}")
	String getStudent(@PathVariable UUID studentId);
	
	@PostMapping("/students")
	String addStudent(@RequestBody Student student);
	
	@PutMapping("/students")
	String updateStudent(@RequestBody Student theStudent);
	
	@DeleteMapping("/students/{studentId}")
	String deleteStudent(@PathVariable UUID studentId);
}


