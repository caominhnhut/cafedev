package com.cafedev.dto;

import java.util.List;

import com.cafedev.enums.EUserRoleName;
import com.cafedev.model.Role;
import com.cafedev.model.User;

public class UserRequestDTO {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String avatar;
	private List<String> roles;

	public User toUser() {
		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setAvatar(avatar);
		if (this.getRoles()==null || this.getRoles().isEmpty()) {
			Role role = new Role();
			role.setName(EUserRoleName.ROLE_USER);
			user.getRoles().add(role);
		} else {
			for (String roleName : this.getRoles()) {
				Role role = new Role();
				role.setName(EUserRoleName.valueOf(roleName));
				user.getRoles().add(role);
			}
		}
		return user;
	}

	@Override
	public String toString() {
		StringBuffer strDto = new StringBuffer();
		strDto.append("User Info:\nUsername: ").append(this.userName).append("\nPassword: ").append(this.password)
				.append("\nfirstName: ").append(this.firstName).append("\nlastName: ").append(this.lastName)
				.append("\nemail: ").append(this.email).append("\nphoneNumber: ").append(this.phoneNumber)
				.append("\navatar: ").append(this.avatar).append("\nroles: ").append(this.roles);
		return strDto.toString();
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
