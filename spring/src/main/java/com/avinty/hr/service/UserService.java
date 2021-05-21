package com.avinty.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinty.hr.data.UserRepo;
import com.avinty.hr.model.User;
import com.avinty.hr.model.UserNotFoundException;

@Service
public class UserService {
	@Autowired
	UserRepo uRepo;

	public void uploadProfilePicture(long id, String profilePicture) throws UserNotFoundException {
		User u = uRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		u.setProfilePicture(profilePicture);
		uRepo.save(u);
	}

	public List<User> getAllUsers() {
		List<User> uList = new ArrayList<>();
		uRepo.findAll().forEach((uList::add));
		return uList;
	}

	public User getUserbyId(long id) throws UserNotFoundException {
		return uRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	public User saveUser(String username, String email, String password, String profilePictureString) {
		User u = new User(username, email, password, profilePictureString);
		return uRepo.save(u);
	}

	public User updateUser(long id, String username, String email, String password, String profilePicture)
			throws UserNotFoundException {
		User u = uRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		u.setUsername(username);
		u.setEmail(email);
		u.setPassword(password);
		u.setProfilePicture(profilePicture);
		return uRepo.save(u);
	}

	public void deleteUser(long id) {
		uRepo.deleteById(id);
	}

}
