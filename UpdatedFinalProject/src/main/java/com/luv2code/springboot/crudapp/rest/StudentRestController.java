package com.luv2code.springboot.crudapp.rest;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.crudapp.entity.Student;
import com.luv2code.springboot.crudapp.service.StudentService;

import Specifications.StudentSpecification;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private StudentService studentService;
	
	@Autowired 
	public StudentRestController(StudentService theStudentService) {
		studentService  = theStudentService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/students/{pageNo}/{recordCount}/sortByName")
	public List<Student> findAllSort(@PathVariable int pageNo, @PathVariable int recordCount, @RequestParam() int value ) {
		return studentService.findAllSort(pageNo,recordCount);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable UUID studentId) {
		Student theStudent = studentService.findById(studentId);
		if (theStudent == null) {
			throw new RuntimeException("Student id not found -" + studentId);
		}
		return theStudent;
	}
	
	//post a student
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student theStudent) {
		studentService.save(theStudent);
		
		return theStudent;
	}
	     
	//update a student
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student theStudent) {
	    UUID studentId = theStudent.getId();
	    Student oldStudent = studentService.findById(studentId);
	    Field[] fields = theStudent.getClass().getDeclaredFields();
	    for (Field field : fields) {
	        try {
	            field.setAccessible(true);
	            Object newValue = field.get(theStudent);
	            if (newValue != null) {
	                field.set(oldStudent, newValue);
	            }
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }
	    studentService.save(oldStudent);
	    return oldStudent;
	}

	
	@DeleteMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable UUID studentId) {
		Student tempStudent = studentService.findById(studentId);
		if(tempStudent == null) {
			throw new RuntimeException("Student not found -" + studentId);
		}
		studentService.deleteById(studentId);
		
		return "Deleted student id-" + studentId;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/students")
    public List<Student> getStudents(@RequestParam(required = false) String firstName,
                                      @RequestParam(required = false) String lastName,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) String consistOf,
                                      @RequestParam(required = false) String endsWith
                                      ) {
        Specification<Student> spec = StudentSpecification.getStudentByCriteria(firstName, lastName, email,consistOf,endsWith);
        List<Student> students = studentService.findAll(spec);

        return students;
    }
	
	
}


