package com.common.util;

import java.io.IOException;
import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author farihal
 *
 */
public class InternalAppenderWrapper extends RollingFileAppender {
	//private static final String THREAD_NAME = "jobLauncherTaskExecutor";
	//private static final String THREAD_NAME = "main";
	final static Logger defaultThreadLogger = Logger.getLogger(DefaultThreadLogger.class);
	protected String threadName = null;
	
	public String getThreadName() {
		return threadName;
	}
	
	public InternalAppenderWrapper() {
		super();		
	}

	public InternalAppenderWrapper(Layout layout, String filename, boolean append) throws IOException {
		super(layout, filename, append);		
	}

	public InternalAppenderWrapper(Layout layout, String filename) throws IOException {
		super(layout, filename);
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public InternalAppenderWrapper(String threadName) {
		super();
		this.threadName = threadName;
	}

	@Override
	public void append(LoggingEvent event) {
		event.getThreadName();
		event.getMessage();
		//Appender appender = defaultThreadLogger.getAllAppenders();
		//System.out.println(defaultThreadLogger.getAllAppenders());
		Enumeration<Appender> appenders = defaultThreadLogger.getAllAppenders();
		Appender defaultAppender = appenders.nextElement();
		//System.out.println( "appender1.getName() " + defaultAppender.getName());
		//System.out.println("Thread name is " + event.getThreadName());		

		if(event.getThreadName().startsWith(this.threadName)) {//spring batch logs
			super.append(event);
		}
		else {
			defaultAppender.doAppend(event);
			
		}
	}

}
