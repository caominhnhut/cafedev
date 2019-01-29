package com.warmup.copy;

public class Execution {

	public static void main(String[] args) {
		
		//Shallow copy;
		Address address = new Address("HCM", "VN");
		User user1 = new User("Nhut", address);
		
		User deep1 = new User(user1);
		User deep2 = (User) user1.clone();
		
		user1.getAddress().setCity("Da Nang");
		
		System.out.println("Shallow copy using contructor: "+user1);
		System.out.println("Deep copy using contructor: "+deep1);
		System.out.println("Deep copy using clone method: "+deep2);
	}

}
