package com.facebook.feed.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.facebook.feed.schema.FeedSchema;


public interface FeedService {

	public void post(FeedSchema feed, HttpSession session);

	public List<FeedSchema> feed(int pageNo);

	public FeedSchema like(String id, HttpSession session);

	public FeedSchema reply(String id, String reply, HttpSession session);

}
