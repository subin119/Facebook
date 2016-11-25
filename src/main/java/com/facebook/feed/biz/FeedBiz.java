package com.facebook.feed.biz;

import java.util.List;

import com.facebook.feed.schema.FeedSchema;
import com.facebook.user.schema.UserSchema;

public interface FeedBiz {

	public void post(FeedSchema feed);

	public List<FeedSchema> feed(int pageNo);

	public void like(String id, UserSchema user);

	public FeedSchema getFeed(String id);

	public void reply(String id, String reply, UserSchema user);

}
