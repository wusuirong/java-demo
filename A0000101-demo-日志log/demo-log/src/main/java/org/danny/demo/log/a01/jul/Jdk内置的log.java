package org.danny.demo.log.a01.jul;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Jdk内置的log {

	public static void main(String[] args) {
		// jdk1.4后就提供了，只是log4j太有名了
		// 配置在jre/lib/logging.properties
		/*
		 * OFF
		 * SEVERE（最高值）
WARNING
INFO
CONFIG
FINE
FINER
FINEST（最低值）
ALL
		 */
		Logger log = Logger.getLogger("org.danny.demo.log.jdk");
		log.setLevel(Level.INFO);
		Logger log1 = Logger.getLogger("org.danny.demo.log.jdk");
		System.out.println(log == log1); // true
		Logger log2 = Logger.getLogger("org.danny.demo.log.jdk.a");
		log2.setLevel(Level.WARNING);
		
		/*
		 * Note:
CustomLogManagerEditor.getLogger返回的logger拥有三个handler，所以记录日志信息的时候，日志信息会被存储到三个区域【log.log,System.out,System.err】。
当启用CustomLogManagerEditor.resetFromPropertyFile("logging/logging.properties");时，日志信息会被记录到控制台，以及u.log文件。
如果即调用CustomLogManagerEditor.resetFromPropertyFile("logging/logging.properties");又通过CustomLogManagerEditor.getLogger返回的logger记录日志，
因为logger有5个handler，所以日志信息会被记录到4个区域【u.log,log.log,System.out,System.err】 其中在System.err中会记录2次。
		 */
//      CustomLogManager.resetFromPropertyFile("logging/logging.properties");  
//      Logger logger=Logger.getLogger(Main.class.getName());

		log.info("aaa");
		log2.info("bbb");
		log2.fine("fine");
	}
}
