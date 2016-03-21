package org.danny.demo.log.a05.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Logback例子 {

	/*
	 * 要注释pom.xml的
	 * <!--dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>1.7.18</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>1.7.18</version>
		</dependency-->
	 * 因为logback自带slf4j实现，会导致slf4j不知应该用log4j还是logback
	 */
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger("org.danny.demo.log.a05.logback");
		logger.error("logback 测试");
	}
}
