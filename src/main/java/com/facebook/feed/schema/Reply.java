package com.facebook.feed.schema;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.facebook.user.schema.UserSchema;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Reply {

	private String message;
	
	@DateTimeFormat(iso = ISO.NONE, pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	private Date registedDate;
	
	private UserSchema user;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserSchema getUser() {
		return user;
	}

	public void setUser(UserSchema user) {
		this.user = user;
	}

	public Date getRegistedDate() {
		return registedDate;
	}

	public void setRegistedDate(Date registedDate) {
		this.registedDate = registedDate;
	}
	
	
}
