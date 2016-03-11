package com.sherwin.examples.net;

import java.net.*;
import java.io.*;
import java.util.Date;

/**
 * A class that displays information about a URL.
 */
public class GetURLInfo {
	/** Use the URLConnection class to get info about the URL */
	public static void printinfo(URL url) throws IOException {
		URLConnection c = url.openConnection(); // Get URLConnection from URL
		c.connect(); // Open a connection to URL

		// Display some information about the URL contents
		System.out.println("  Content Type: " + c.getContentType());
		System.out.println("  Content Encoding: " + c.getContentEncoding());
		System.out.println("  Content Length: " + c.getContentLength());
		System.out.println("  Date: " + new Date(c.getDate()));
		System.out.println("  Last Modified: " + new Date(c.getLastModified()));
		System.out.println("  Expiration: " + new Date(c.getExpiration()));

		// If it is an HTTP connection, display some additional information.
		if (c instanceof HttpURLConnection) {
			HttpURLConnection h = (HttpURLConnection) c;
			System.out.println("  Request Method: " + h.getRequestMethod());
			System.out.println("  Response Message: " + h.getResponseMessage());
			System.out.println("  Response Code: " + h.getResponseCode());
		}
	}

	/** Create a URL, call printinfo() to display information about it. */
	public static void main(String[] args) {
		args = new String[] { "http://www.g.cn" };
		try {
			printinfo(new URL(args[0]));
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("Usage: java GetURLInfo <url>");
		}
	}
}
