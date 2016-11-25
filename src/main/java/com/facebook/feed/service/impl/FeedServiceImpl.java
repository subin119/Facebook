package com.facebook.feed.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facebook.common.constants.Session;
import com.facebook.feed.biz.FeedBiz;
import com.facebook.feed.schema.FeedSchema;
import com.facebook.feed.service.FeedService;
import com.facebook.user.schema.UserSchema;

@Service
public class FeedServiceImpl implements FeedService {
	
	@Autowired
	private FeedBiz feedBiz;

	@Override
	public void post(FeedSchema feed, HttpSession session) {
		UserSchema user = (UserSchema) session.getAttribute(Session.USER);
		feed.setUser(user);
		feed.setPostedDate(new Date());
		feedBiz.post(feed);
		
	}

	@Override
	public List<FeedSchema> feed(int pageNo) {
		 
		return feedBiz.feed(pageNo);
	}

	@Override
	public FeedSchema like(String id, HttpSession session) {
		UserSchema user = (UserSchema) session.getAttribute(Session.USER);
		
		feedBiz.like(id,user);
		return feedBiz.getFeed(id);
		
	}

	@Override
	public FeedSchema reply(String id, String reply, HttpSession session) {
		UserSchema user = (UserSchema) session.getAttribute(Session.USER);
		
		feedBiz.reply(id, reply, user);
		return feedBiz.getFeed(id);
	}
	
	
}
