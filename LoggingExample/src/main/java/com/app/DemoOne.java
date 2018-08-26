package com.app;

import org.apache.log4j.Logger;

public class DemoOne{
	
	final static Logger logger = Logger.getLogger(DemoOne.class);
	
	public static void main(String[] args) {
	
		DemoOne demo1 = new DemoOne();
		LoggingSample logSample = new LoggingSample();
		demo1.runMe(">> Demo One <<");
		logSample.testSecond(">> Logging Sample <<");
		
	}
	
	private void runMe(String parameter){
		
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
		
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
		
	}
	
}