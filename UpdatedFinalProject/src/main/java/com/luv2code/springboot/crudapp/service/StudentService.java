package com.luv2code.springboot.crudapp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.luv2code.springboot.crudapp.entity.Student;

public interface StudentService {

	public List<Student> findAll(Specification<Student> specifications);
	
	public List<Student> findAllSort(int pageNo,int recordCount);
	
	public Student findById(UUID studentId);

	public void save(Student theStudent);

	public void deleteById(UUID theId);
	
	
}
