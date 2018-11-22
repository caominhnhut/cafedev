package com.cafedev.dto;

import com.cafedev.model.User;

public class UserRequestDTO {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String avatar;

	public User toUser(){
		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setAvatar(avatar);
		return user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
