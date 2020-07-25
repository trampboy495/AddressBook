package com.bridgelabz.service;

public class Person {
	private final String firstName; 
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	public Person(String firstName, String lastName, String address, String city,
			String state, String zip, String phone) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.phone=phone;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	public String getPhoneNo() {
		return phone;
	}

}
