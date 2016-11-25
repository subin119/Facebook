package com.facebook.feed.schema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.facebook.user.schema.UserSchema;
import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "feeds")
public class FeedSchema {

	@Id
	private String id;

	private String message;

	@DateTimeFormat(iso = ISO.NONE, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date postedDate;

	private UserSchema user;

	private List<UserSchema> like;
	private int likeCount;

	private List<Reply> replies;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public UserSchema getUser() {
		return user;
	}

	public void setUser(UserSchema user) {
		this.user = user;
	}

	public List<UserSchema> getLike() {
		return like;
	}

	public void setLike(List<UserSchema> like) {
		this.like = like;
		this.likeCount = getLikeCount();
	}

	public int getLikeCount() {
		if (like == null) {
			like = new ArrayList<UserSchema>();
		}

		return like.size();
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public List<Reply> getReplies() {
		if(replies == null) {
			replies = new ArrayList<Reply>();
		}
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}