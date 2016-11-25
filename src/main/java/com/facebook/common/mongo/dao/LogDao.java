package com.facebook.common.mongo.dao;

import com.facebook.common.mongo.schema.LogSchema;

public interface LogDao {

	public void writeLog(LogSchema loggerVO);
}
