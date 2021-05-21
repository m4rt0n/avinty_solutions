package com.avinty.hr.model;

public class EmployeeNotFoundException extends Exception {

	public EmployeeNotFoundException(long id) {
		super(String.format("Employee is not found: %s", id));
	}

}
