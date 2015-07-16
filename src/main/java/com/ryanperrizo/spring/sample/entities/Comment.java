package com.ryanperrizo.spring.sample.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="comments")
//@SequenceGenerator(name="seq", initialValue=1, allocationSize=200)
public class Comment {
	private static final int COMMENT_MAX = 400;
	private static final int NAME_MAX = 50;

	@Id // for primary key
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, length = NAME_MAX)
	private String name;//provides information on commenter
	
	@Column(nullable = false, length = COMMENT_MAX)
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String toString(){
		return "Comment from: " + name + " content: " + comment + " id: " + id;
	}
	
	
}
