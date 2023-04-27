package com.microservices.microservices2.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.microservices2.entity.Student;
import com.microservices.microservices2.util.FeignServiceutil;

@RestController
@RequestMapping("/demo")
public class feigndemocontroller {
	@Autowired
	private FeignServiceutil feignserviceutil;
	
	@GetMapping("/teacher-student")
	public String getName() {
		return feignserviceutil.getName();
	}
	@GetMapping("/teacher-student/{studentId}")
	public String getStudent(@PathVariable UUID studentId) {
		return feignserviceutil.getStudent(studentId);
	}
	
	@PostMapping(value = "/teacher-student", produces = "application/json")
	public String addStudent(@RequestBody Student student) {
		return feignserviceutil.addStudent(student);
	}
	
	@PutMapping("/teacher-student")
	public String updateStudent(@RequestBody Student theStudent) {
		return feignserviceutil.updateStudent(theStudent);
	}
	
	@DeleteMapping("teacher-student/{studentId}")
	public String deleteStudent(@PathVariable UUID studentId) {
		return feignserviceutil.deleteStudent(studentId);
	}
}
