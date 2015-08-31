package com.ryanperrizo.spring.sample.dto;

public interface ApplicantForm {
	public abstract String getState();

	public abstract void setState(String state);

	public abstract String getZip();

	public abstract void setZip(String zip);

	public abstract String getCity();

	public abstract void setCity(String city);

	public abstract String getDarkestSecret();

	public abstract void setDarkestSecret(String darkestSecret);

	public abstract String getFirstName();

	public abstract void setFirstName(String firstName);

	public abstract String getLastName();

	public abstract void setLastName(String lastName);

	public abstract String getBirthDate();

	public abstract void setBirthDate(String birthDate);

	public abstract String getSsn();

	public abstract void setSsn(String ssn);
	
	public abstract String getAddress();

	public abstract void setAddress(String address);

	public abstract String getGender();

	public abstract void setGender(String gender);
}
