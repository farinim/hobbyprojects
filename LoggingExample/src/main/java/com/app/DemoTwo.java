package com.app;

import org.apache.log4j.Logger;

public class DemoTwo{
	
	final static Logger logger = Logger.getLogger(DemoTwo.class);
	
	public static void main(String[] args) {
	
		DemoTwo obj = new DemoTwo();
		
		try{
			obj.divide();
		}catch(ArithmeticException ex){
			logger.error("Sorry, something wrong!", ex);
		}
		
		
	}
	
	private void divide(){
		
		int i = 10 /0;

	}
	
}