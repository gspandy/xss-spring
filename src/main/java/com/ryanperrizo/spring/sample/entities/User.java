package com.ryanperrizo.spring.sample.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.ryanperrizo.spring.sample.util.MyUtil;

@Entity
@Table(name="usr", indexes = {
		@Index(columnList = "email", unique=true),
		@Index(columnList = "forgotPasswordCode", unique=true)
})
public class User {
	
	public static final int EMAIL_MAX = 250;
	public static final int NAME_MAX = 50;
	public static final String EMAIL_PATTERN = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	public static final int RANDOM_CODE_LENGTH = 16;

	public static enum Role {
		UNVERIFIED, BLOCKED, ADMIN
	}
	@Id // for primary key
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = EMAIL_MAX)
	private String email;
	
	@Column(nullable = false, length = NAME_MAX)
	private String name;
	
	// no length because it will be encrypted
	@Column(nullable = false)
	private String password;
	
	@Column(length = RANDOM_CODE_LENGTH)
	private String forgotPasswordCode;
	
	

	public static String getEmailPattern() {
		return EMAIL_PATTERN;
	}

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

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(length = 16)
	private String verificationCode;
	
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<Role>();

	public boolean isAdmin() {
		return roles.contains("ADMIN");
	}
	
	public boolean isEditable(){
		User loggedIn = MyUtil.getSessionUser();
		if (loggedIn == null)
			return false;
		return loggedIn.isAdmin() || loggedIn.getId() == id;
	}

	public void setForgotPasswordCode(String forgotPasswordCode) {
		this.forgotPasswordCode = forgotPasswordCode;
	}
	
	public String getForgotPasswordCode() {
		return forgotPasswordCode;
	}

}
