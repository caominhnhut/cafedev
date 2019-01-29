package com.warmup.copy;

public class User {

	private String name;
	
	private Address address;
	
	@Override
	public String toString(){
		return "name: "+this.name+" - city: "+this.address.getCity()+" - country: "+this.address.getCountry();
	}

	public User(String name, Address address){
		this.name = name;
		this.address = address;
	}
	
	public User(User that){
		this(that.name, new Address(that.getAddress()));
	}
	
	@Override
	public Object clone(){
		User user = null;
		try {
			user = (User) super.clone();
		} catch (CloneNotSupportedException e) {
			user = new User(this.name, this.address);
		}
		user.address = (Address) this.address.clone();
		return user;
	}
	
	//for shallow copy;
//	public User(User original){
//		this.name = original.name;
//		this.address = original.address;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
