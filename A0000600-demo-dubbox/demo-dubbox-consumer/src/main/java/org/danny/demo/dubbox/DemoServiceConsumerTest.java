package org.danny.demo.dubbox;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.danny.demo.dubbox.DemoService;

/**
 * 
 * @author wusuirong
 *
 * @Description <li></li>
 *
 * @Email wusuirong@xxx.com
 *
 *        www.xxx.com Copyright (c) 2014 All Rights Reserved.
 */
public class DemoServiceConsumerTest {

	private static final Logger logger = Logger.getLogger(DemoServiceConsumerTest.class);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("开始调用");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
						new String[] { "applicationContext-service.xml" });
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		String output = demoService.doService("我要钱");
		System.out.println(output); // 显示调用结果
		System.exit(0);
	}
}
