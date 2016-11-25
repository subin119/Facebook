package com.facebook.common.mongo.logger;

import org.slf4j.Logger;

import com.facebook.common.mongo.dao.LogDao;
import com.facebook.common.mongo.schema.LogSchema;

public class MongoDBLogger {

	private Logger logger;
	private LogDao logDao;

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public <T> void trace(String message, T object) {
		logger.trace(message);
		
		LogSchema loggerVO = new LogSchema();
		loggerVO.setMessage(message);
		loggerVO.setType("trace");
		loggerVO.setObject(object);
		
		logDao.writeLog(loggerVO); 
	}

	public <T> void debug(String message, T object) {
		logger.debug(message);
		
		LogSchema loggerVO = new LogSchema();
		loggerVO.setMessage(message);
		loggerVO.setType("debug");
		loggerVO.setObject(object);
		
		logDao.writeLog(loggerVO); 
	}

	public <T> void info(String message, T object) {
		logger.info(message);
		
		LogSchema loggerVO = new LogSchema();
		loggerVO.setMessage(message);
		loggerVO.setType("info");
		loggerVO.setObject(object);
		
		logDao.writeLog(loggerVO); 
	}

	public <T> void warn(String message, T object) {
		logger.warn(message);
		
		LogSchema loggerVO = new LogSchema();
		loggerVO.setMessage(message);
		loggerVO.setType("warn");
		loggerVO.setObject(object);
		
		logDao.writeLog(loggerVO); 
	}

	public <T> void error(String message, T object) {
		logger.error(message);
		
		LogSchema loggerVO = new LogSchema();
		loggerVO.setMessage(message);
		loggerVO.setType("error");
		loggerVO.setObject(object);
		
		logDao.writeLog(loggerVO); 
	}

}
