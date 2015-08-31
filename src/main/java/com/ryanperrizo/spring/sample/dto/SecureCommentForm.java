package com.ryanperrizo.spring.sample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SecureCommentForm implements CommentForm{
	
	@NotNull
	@Size(min=1, max=255, message="{sizeErrorMessage}")
	@Pattern(regexp="([0-9|a-z|A-Z|\\_|\\@|\\.|\\s])*", message="{searchErrorMessage}")
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}