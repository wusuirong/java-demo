package org.danny.demo.log.a01.jul;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Jdk内置的log的格式化输出 {

	public static void main(String[] args) throws SecurityException, IOException {
		Logger log = Logger.getLogger("org.danny.demo.log.jdk");
		log.setLevel(Level.INFO);
		Logger log1 = Logger.getLogger("org.danny.demo.log.jdk");
		System.out.println(log == log1); // true
		Logger log2 = Logger.getLogger("org.danny.demo.log.jdk");
		// log2.setLevel(Level.WARNING);

		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		log.addHandler(consoleHandler);
		FileHandler fileHandler = new FileHandler("C:/testlog%g.log");
		fileHandler.setLevel(Level.INFO);
		fileHandler.setFormatter(new MyLogFormatter());
		log.addHandler(fileHandler);

		log.info("aaa");
		log2.info("bbb");
		log2.fine("fine");
	}
}

class MyLogFormatter extends Formatter {
	@Override
	public String format(LogRecord record) {
		return record.getLevel() + ":" + record.getMessage() + "\n";
	}
}
