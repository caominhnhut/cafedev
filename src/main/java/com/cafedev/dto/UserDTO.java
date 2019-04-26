package com.cafedev.dto;

import com.cafedev.model.User;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String avatar;
	private String email;
	private String phoneNumber;

	public void copyFrom(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.avatar = user.getAvatar();
		this.email= user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
