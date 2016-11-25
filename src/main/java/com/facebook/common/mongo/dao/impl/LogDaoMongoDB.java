package com.facebook.common.mongo.dao.impl;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.facebook.common.mongo.dao.LogDao;
import com.facebook.common.mongo.schema.LogSchema;

public class LogDaoMongoDB implements LogDao {

	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void writeLog(LogSchema loggerVO) {
		mongoTemplate.insert(loggerVO);
	}
	
}
