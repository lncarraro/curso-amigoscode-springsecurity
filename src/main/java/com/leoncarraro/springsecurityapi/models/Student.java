package com.leoncarraro.springsecurityapi.models;

public class Student {

	private final Long id;
	private final String name;
	
	public Student(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
