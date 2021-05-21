package com.avinty.hr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 20)
	private String username;

	@Column(length = 20)
	private String email;

	@Column(length = 20)
	private String password;

	@Column(columnDefinition = "text")
	private String profilePicture;

	public User(String username, String email, String password, String profilePicture) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.profilePicture = profilePicture;
	}
}
