package com.ryanperrizo.spring.sample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ryanperrizo.spring.sample.entities.User;


public class SignupForm {
		
		@NotNull
		@Size(min=1, max=255, message="{sizeErrorMessage}")
		@Pattern(regexp=User.EMAIL_PATTERN, message="{emailErrorMessage}")
		private String email;
		
		@NotNull
		@Size(min=1, max=255, message="{sizeErrorMessage}") 
		private String name;
		@NotNull
		@Size(min=8, max=255, message="{sizeErrorMessage}")
		private String password;
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getName() {
			return name;
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
		public String toString(){
			return "SignupForm [email=" + email + " name=" + name + " password=" + password +"]";
		}
		
}
