package com.cafedev.common;

public class RegexMatcher {

	public static boolean isValidName(String s){
		return s.matches("^[a-zA-Z0-9\\s+_.-]+$");
	}
	
	public static boolean isValidEmail(String email){
		return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}
	
	public static boolean isValidPhone(String phone){
		if(phone.length() < 10){
			return false;
		}
		return phone.matches("[0-9]+");
	}
}
