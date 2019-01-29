package com.warmup.copy;

public class Address {

	private String city;
	
	private String country;

	//for deep copy;
	public Address(Address that){
		this(that.getCity(), that.getCountry());
	}
	
	//for shallow copy;
	public Address(String city, String country){
		this.city = city;
		this.country = country;
	}
	
	@Override
	public Object clone(){
		try {
			return (Address) super.clone();
		} catch (CloneNotSupportedException e) {
			return new Address(this.city, this.country);
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
