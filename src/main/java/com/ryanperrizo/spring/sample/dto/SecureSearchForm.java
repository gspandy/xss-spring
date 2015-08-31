package com.ryanperrizo.spring.sample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.owasp.encoder.Encode;
import org.owasp.encoder.Encoder;

public class SecureSearchForm{
	
	@NotNull
	@Size(min=1, max=50, message="{sizeErrorMessage}")
	@Pattern(regexp="([0-9|a-z|A-Z|\\_|\\@|\\.|\\s])*", message="{searchErrorMessage}")
	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	
}
