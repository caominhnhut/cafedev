package com.warmup.exception;

public class MyException {

	public static void main(String[] args) {
		
		int age = 20;
		try {
			AgeUltil.checkAge(age);
		} catch (YoungException | OldException e) {
			System.err.println(e.getMessage());
		}
	}

}
