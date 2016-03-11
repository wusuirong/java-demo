package com.sherwin.examples.net;

import java.io.*;
import java.net.*;

/**
 * @author sherwin
 */
public class GetURL {

	public static void getUrl(String[] args) {
		InputStream in = null;
		OutputStream out = null;
		try {
			if ((args.length != 1) && (args.length != 2)) {
				throw new IllegalArgumentException("Wrong number of args");
			}

			URL url = new URL(args[0]); // Create the URL
			in = url.openStream(); // Open a stream to it
			if (args.length == 2) // Get an appropriate output stream
				out = new FileOutputStream(args[1]);
			else
				out = System.out;

			byte[] buffer = new byte[4096];
			int bytes_read;
			while ((bytes_read = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytes_read);
			}
		}
		// On exceptions, print error message and usage message.
		catch (Exception e) {
			System.err.println("Usage: java GetURL <URL> [<filename>]");
		} finally { // Always close the streams, no matter what.
			try {
				in.close();
				out.close();
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		args = new String[] { "http://www.baidu.com", "e:/baidu.html" };
		GetURL.getUrl(args);

		args = new String[] { "http://t3.baidu.com/it/u=3837158154,3560629380&fm=2&gp=25.jpg", "e:/灰太狼.jpg" };
		GetURL.getUrl(args);
	}
}
