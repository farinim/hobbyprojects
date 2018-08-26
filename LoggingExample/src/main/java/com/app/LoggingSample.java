package com.app;

import org.apache.log4j.Logger;

public class LoggingSample {

	final static Logger logger = Logger.getLogger(LoggingSample.class);
	public void testSecond(String parameter) {
		logger.error("This is error : " + parameter);
	}
}
