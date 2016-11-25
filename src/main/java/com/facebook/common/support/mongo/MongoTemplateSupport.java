package com.facebook.common.support.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author mcjan
 *
 */
@Component
public class MongoTemplateSupport {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongo() {
		return mongoTemplate;
	}
	
}
