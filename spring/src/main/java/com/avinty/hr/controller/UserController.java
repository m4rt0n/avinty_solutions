package com.avinty.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avinty.hr.model.User;
import com.avinty.hr.model.UserNotFoundException;
import com.avinty.hr.service.UserService;

@RestController
@RequestMapping(path = "/API/V1/users")
public class UserController {
	@Autowired
	UserService uService;

	@PostMapping("/{id}/uploadProfilePics")
	public void uploadProfilePicture(@PathVariable("id") long id, @RequestBody String profilePicture)
			throws UserNotFoundException {
		uService.uploadProfilePicture(id, profilePicture);
	}

	@GetMapping
	public void getAllUsers() {
		uService.getAllUsers();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") long id) throws UserNotFoundException {
		return uService.getUserbyId(id);
	}

	@PostMapping("/createuser")
	public User createUser(@RequestParam(value = "username") String username,
			@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
			@RequestParam(value = "profilePicture") String profilePicture) {
		return uService.saveUser(username, email, password, profilePicture);
	}

	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") long id, @RequestParam(value = "username") String username,
			@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
			@RequestParam(value = "profilepicture") String profilePicture) throws UserNotFoundException {
		return uService.updateUser(id, username, email, password, profilePicture);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") long id) {
		uService.deleteUser(id);
	}
}
