package com.ryanperrizo.spring.sample.dto;

import org.owasp.encoder.Encode;
import org.owasp.encoder.Encoder;

public class InsecureSearchForm{
	
	private String search;
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}