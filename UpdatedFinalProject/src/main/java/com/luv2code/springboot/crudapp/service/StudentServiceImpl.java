package com.luv2code.springboot.crudapp.service;

import java.util.List; 
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.crudapp.dao.StudentRepository;
import com.luv2code.springboot.crudapp.entity.Student;



@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAllSort(int pageNo, int recordCount) {
        Pageable pageable = PageRequest.of(pageNo, recordCount, Sort.by("firstName"));
        return studentRepository.findAll(pageable).get().toList();
    } 

    @Override
    public Student findById(UUID theId) {
        Optional<Student> result = studentRepository.findById(theId);

        Student theStudent = null;
        if (result.isPresent()) {
            theStudent = result.get();
        } else {
            throw new RuntimeException("Did not find student id - " + theId);
        }
        return theStudent;
    }

    @Override
    public void save(Student theStudent) {
        if (theStudent.getId() == null) {
            theStudent.setId(UUID.randomUUID());
        }
        studentRepository.save(theStudent);
    }

    @Override
    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAll(Specification<Student> specifications) {
        List<Student> result = studentRepository.findAll(specifications);
        return result;
    }
}

