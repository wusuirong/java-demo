package org.danny.demo.log.a04.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4j的例子 {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger("org.danny.demo.log.a04.slf4j");
		logger.error("slf4j测试");
	}
}
