package com.luv2code.springboot.crudapp.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.luv2code.springboot.crudapp.entity.Student;


public interface StudentRepository extends JpaRepository<Student, UUID>, JpaSpecificationExecutor<Student> {

 
}


