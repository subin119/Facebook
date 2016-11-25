package com.facebook.feed.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.facebook.common.support.mongo.MongoTemplateSupport;
import com.facebook.common.util.pager.Pager;
import com.facebook.feed.dao.FeedDao;
import com.facebook.feed.schema.FeedSchema;
import com.facebook.feed.schema.Reply;
import com.facebook.user.schema.UserSchema;

@Repository
public class FeedDaoImpl extends MongoTemplateSupport implements FeedDao {

	@Override
	public void post(FeedSchema feed) {
		getMongo().save(feed);
	}

	@Override
	public List<FeedSchema> feed(Pager pager) {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC,"_id"));
		query.skip(pager.getStartArticleNumber());
		query.limit(pager.getEndArticleNumber());
		
		
		return getMongo().find(query, FeedSchema.class);
	}

	@Override
	public int feedCount() {
		return (int) getMongo().count(new Query(), "feeds");
	}

	@Override
	public void like(String id, UserSchema user) {
		
		Criteria criteria = new Criteria("_id");
		criteria.is(id);
		
		Query query = new Query(criteria);
		
		Update update = new Update();
		update.push("like",user);
		
		getMongo().updateFirst(query, update, "feeds");
		
	}

	@Override
	public FeedSchema getFeed(String id) {
		return getMongo().findById(id, FeedSchema.class,"feeds");
	}
	
	@Override
	public void reply(String id, String reply, UserSchema user) {
		Reply replyObject = new Reply();
		replyObject.setMessage(reply);
		replyObject.setRegistedDate(new Date());
		replyObject.setUser(user);

		Criteria criteria = new Criteria("_id");
		criteria.is(id);
		
		Query query = new Query(criteria);
		
		Update update = new Update();
		update.push("replies", replyObject);
		
		getMongo().updateFirst(query, update, "feeds");
	}

}
