package com.ryanperrizo.spring.sample.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SecureApplicantForm implements ApplicantForm{
	
	@NotNull
	@Size(min=1, max=50, message="{sizeErrorMessage}")
	@Pattern(regexp="([a-z|A-Z|\\'|\\s])*", message="{textErrorMessage}")
	private String firstName;
	
	@NotNull
	@Size(min=1, max=50, message="{sizeErrorMessage}")
	@Pattern(regexp="([a-z|A-Z|\\'|\\s])*", message="{textErrorMessage}")
	private String lastName;
	
	@NotNull
	@Size(min=1, max=10, message="{sizeErrorMessage}")
	@Pattern(regexp="([0-9|\\/])*", message="{dateErrorMessage}")
	private String birthDate;
	
	@NotNull
	@Size(min=3, max=9, message="{sizeErrorMessage}")
	@Pattern(regexp="([0-9|a-z|A-Z|\\_|\\@|\\.|\\s])*", message="{searchErrorMessage}")
	private String ssn;
	
	@NotNull
	@Size(min=1, max=50, message="{sizeErrorMessage}")
	@Pattern(regexp="([0-9|a-z|A-Z|\\-|\\.|\\s])*", message="{textErrorMessage}")
	private String address;
	
	@NotNull
	@Size(min=1, max=15, message="{sizeErrorMessage}")
	@Pattern(regexp="([a-z|A-Z|\\s])*", message="{textErrorMessage}")
	private String gender;
	
	@NotNull
	@Size(min=1, max=2, message="{sizeErrorMessage}")
	@Pattern(regexp="([A-Z])*", message="{stateErrorMessage}")
	private String state;
	
	@NotNull
	@Size(min=5, max=5, message="{sizeErrorMessage}")
	@Pattern(regexp="([0-9])*", message="{zipErrorMessage}")
	private String zip;
	
	@NotNull
	@Size(min=1, max=20, message="{sizeErrorMessage}")
	@Pattern(regexp="([a-z|A-Z|\\'|\\.|\\s])*", message="{textErrorMessage}")
	private String city;
	
	@NotNull
	@Size(min=1, max=200, message="{sizeErrorMessage}")
	@Pattern(regexp="([0-9|a-z|A-Z|\\'|\\-|\\.|\\s])*", message="{textErrorMessage}")
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
