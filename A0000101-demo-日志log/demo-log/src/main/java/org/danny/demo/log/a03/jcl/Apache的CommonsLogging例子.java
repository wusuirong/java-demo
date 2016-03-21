package org.danny.demo.log.a03.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Apache的CommonsLogging例子 {

	public static void main(String[] args) {
		// commons-logging是日志系统的接口api，它负责桥接到某个实际实现上
		Log log = LogFactory.getLog("org.danny.demo.log.acl");
		log.error("测试");
	}
}
