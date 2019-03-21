package com.warmup.exception;

public class AgeUltil {

	public static void checkAge(int age) throws YoungException, OldException{
		if(age < 18){
			throw new YoungException("Too young");
		}else if(age > 40){
			throw new OldException("too old");
		}
		System.out.println(String.format("Age: %s", age));
	}
	
}
