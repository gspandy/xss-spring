package com.ryanperrizo.spring.sample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsecureCommentForm implements CommentForm{
	
	@NotNull
	@Size(min=1, max=255, message="{sizeErrorMessage}")
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
