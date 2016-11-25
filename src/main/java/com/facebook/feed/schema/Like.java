package com.facebook.feed.schema;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.facebook.user.schema.UserSchema;

public class Like {
	
	private List<UserSchema> users;
	
	//우리가 계산할 거라서 안넣어줌
	@Transient
	private int likeCount;

	public List<UserSchema> getUsers() {
		return users;
	}

	public void setUsers(List<UserSchema> users) {
		this.users = users;
		likeCount = getLikeCount();
		
	}

	public int getLikeCount() {
		
		if( users == null ){
			users = new ArrayList<UserSchema>();
		}
		return users.size();
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
}
