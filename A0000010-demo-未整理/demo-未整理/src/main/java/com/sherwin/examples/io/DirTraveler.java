package com.sherwin.examples.io;

import java.io.*;

public class DirTraveler {

	public static void main(String[] args) {
		//把本文件copy到项目根目录下
		args = new String[] { new String("E:/dba88/softwares") };

		if (args.length != 1) {
			System.err.println("Usage: java DirTraveler <file or directory>");
			System.exit(0);
		}
		try {
			travel(args[0]);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void travel(String dirname) {
		File f = new File(dirname);

		if (!f.exists()) {
			fail("DirTraveler: no such file or directory: " + dirname);
		}
		System.out.println(f.getPath());
		if (f.isDirectory()) {
			String[] files = f.list();
			if (files.length > 0) {
				for(int i=0; i<files.length; i++) {
					travel(dirname + '/' + files[i]);
				}
			}
		}
	}

	protected static void fail(String msg) throws IllegalArgumentException {
		throw new IllegalArgumentException(msg);
	}
}
