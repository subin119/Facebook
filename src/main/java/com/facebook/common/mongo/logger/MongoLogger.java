package com.facebook.common.mongo.logger;

import org.slf4j.Logger;

public class MongoLogger {

	private static MongoDBLogger mongoDBLogger;

	public void setMongoDBLogger(MongoDBLogger mongoDBLogger) {
		MongoLogger.mongoDBLogger = mongoDBLogger;
	}

	public static MongoDBLogger getMongoDBLogger(Logger logger) {
		MongoLogger.mongoDBLogger.setLogger(logger);
		return MongoLogger.mongoDBLogger;
	}

}
