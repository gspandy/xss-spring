package com.ryanperrizo.spring.sample.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="applications")
public class Applicant {
	
	public static final int MAX_LENGTH = 200;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Override
	public String toString() {
		return "Applicant [id=" + id + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", ssn=" + ssn + ", address=" + address
				+ ", gender=" + gender + ", state=" + state + ", zip=" + zip
				+ ", city=" + city + ", darkestSecret=" + darkestSecret + "]";
	}

	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String birthDate;
	
	@Column(nullable = false)
	private String ssn;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String zip;
	
	@Column(nullable = false)
	private String city;
	
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

	@Column(nullable = false, length = MAX_LENGTH)
	private String darkestSecret;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
