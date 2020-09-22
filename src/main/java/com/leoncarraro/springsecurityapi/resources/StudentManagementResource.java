package com.leoncarraro.springsecurityapi.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leoncarraro.springsecurityapi.models.Student;

@RestController
@RequestMapping(value = "management/api/v1/students")
public class StudentManagementResource {

	private static final List<Student> STUDENTS = Arrays.asList(
		new Student(1L, "James Bond"),
		new Student(2L, "Maria Jones"),
		new Student(3L, "Anna Smith"));
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Student> findAll() {
		return STUDENTS;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void insert(@RequestBody Student student) {
		System.out.println("Insert...");
		System.out.println(student);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable Long id) {
		System.out.println("Delete...");
		System.out.println(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public void update(@PathVariable Long id, @RequestBody Student student) {
		System.out.println("Updating student " + id + " - " + student);
	}
	
}
