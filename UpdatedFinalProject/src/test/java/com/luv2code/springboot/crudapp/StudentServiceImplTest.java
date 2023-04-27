package com.luv2code.springboot.crudapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import com.luv2code.springboot.crudapp.dao.StudentRepository;
import com.luv2code.springboot.crudapp.entity.Student;
import com.luv2code.springboot.crudapp.service.StudentServiceImpl;

import Specifications.StudentSpecification;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test 
    public void testFindById() {
        UUID id = UUID.randomUUID();
        Student student = new Student(id, "John", "Doe", "johndoe@test.com");
        Optional<Student> optionalStudent = Optional.of(student);

        when(studentRepository.findById(id)).thenReturn(optionalStudent);

        Student foundStudent = studentService.findById(id);

        assertEquals(student, foundStudent);
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNotFound() {
        UUID id = UUID.randomUUID();
        Optional<Student> optionalStudent = Optional.empty();

        when(studentRepository.findById(id)).thenReturn(optionalStudent);

        Student foundStudent = studentService.findById(id);

        assertEquals(null, foundStudent);
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        UUID id = UUID.randomUUID();
        Student student = new Student(id, "John", "Doe", "johndoe@test.com");

        studentService.save(student);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testDeleteById() {
        UUID id = UUID.randomUUID();
        studentService.deleteById(id);

        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindAll() {
        UUID id = UUID.randomUUID();
        List<Student> students = new ArrayList<>();
        students.add(new Student(id, "John", "Doe", "johndoe@test.com"));
        students.add(new Student(id, "Jane", "Doe", "janedoe@test.com"));

        Specification<Student> spec = StudentSpecification.getStudentByCriteria(null, null, null, null, null);

        when(studentRepository.findAll(spec)).thenReturn(students);

        List<Student> foundStudents = studentService.findAll(spec);

        assertEquals(students, foundStudents);
        verify(studentRepository, times(1)).findAll(spec);
    }
}

