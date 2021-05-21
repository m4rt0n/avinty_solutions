package com.avinty.hr.model;

public class DepartmentNotFoundException extends Exception {

	public DepartmentNotFoundException(long id) {
		super(String.format("Department is not found: %s", id));
	}

}
