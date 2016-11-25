package com.facebook.feed.dao;

import java.util.List;

import com.facebook.common.util.pager.Pager;
import com.facebook.feed.schema.FeedSchema;
import com.facebook.user.schema.UserSchema;

public interface FeedDao {

	public void post(FeedSchema feed);

	public List<FeedSchema> feed(Pager pager);

	public int feedCount();

	public void like(String id, UserSchema user);

	public FeedSchema getFeed(String id);

	public void reply(String id, String reply, UserSchema user);

}
