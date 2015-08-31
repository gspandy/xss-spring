package com.ryanperrizo.spring.sample.dto;

public class InsecureApplicantForm implements ApplicantForm{
	
	private String firstName;
	
	private String lastName;
	
	private String birthDate;
	
	private String ssn;
	
	private String address;
	
	private String gender;
	
	private String state;
	
	private String zip;
	
	private String city;
	
	private String darkestSecret;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDarkestSecret() {
		return darkestSecret;
	}

	public void setDarkestSecret(String darkestSecret) {
		this.darkestSecret = darkestSecret;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}