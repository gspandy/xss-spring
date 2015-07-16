package com.ryanperrizo.spring.sample.dto;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ryanperrizo.spring.sample.entities.User;
import com.ryanperrizo.spring.sample.entities.User.Role;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = -5413245158016266289L;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserDetailsImpl(User user){
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			
			Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(
					user.getRoles().size() + 1);
			//tells the security context what the user has for roles by adding all the roles to the user
			for (Role role : user.getRoles())
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
	
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	
			return authorities;
			
		}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
