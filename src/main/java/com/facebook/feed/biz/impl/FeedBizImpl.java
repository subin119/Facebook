package com.facebook.feed.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.facebook.common.util.pager.Pager;
import com.facebook.common.util.pager.PagerFactory;
import com.facebook.feed.biz.FeedBiz;
import com.facebook.feed.dao.FeedDao;
import com.facebook.feed.schema.FeedSchema;
import com.facebook.user.schema.UserSchema;

@Component("feedBiz")
public class FeedBizImpl implements FeedBiz {

	@Autowired
	private FeedDao feedDao;

	@Override
	public void post(FeedSchema feed) {
		
		String message = feed.getMessage();
		message = message.replaceAll("\n", "<br/>");
		message = message.replaceAll("\n", "");
		feed.setMessage(message);
		
		feedDao.post(feed);
	}

	@Override
	public List<FeedSchema> feed(int pageNo) {
		Pager pager = PagerFactory.getPager(Pager.OTHER);
		pager.setPageNumber(pageNo);
		pager.setTotalArticleCount(feedDao.feedCount());
				
		return feedDao.feed(pager);
	}

	@Override
	public void like(String id,UserSchema user) {
		feedDao.like(id,user);
	}

	@Override
	public FeedSchema getFeed(String id) {
		return feedDao.getFeed(id);
	}

	@Override
	public void reply(String id, String reply, UserSchema user) {
		feedDao.reply(id, reply, user);
	}
	
}
