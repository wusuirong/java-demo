package org.danny.demo.log.a02.log4j;

import org.apache.log4j.Logger;

public class Log4j的例子 {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger("org.danny.demo.log.log4j");
		
		logger.info("test");
	}
}
