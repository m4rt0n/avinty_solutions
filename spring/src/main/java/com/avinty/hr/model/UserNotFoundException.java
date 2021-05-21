package com.avinty.hr.model;

public class UserNotFoundException extends Exception {

	public UserNotFoundException(long id) {
		super(String.format("User is not found: %s", id));
	}

}
