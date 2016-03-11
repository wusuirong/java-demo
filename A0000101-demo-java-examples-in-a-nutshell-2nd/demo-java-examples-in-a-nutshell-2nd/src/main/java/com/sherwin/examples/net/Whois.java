package com.sherwin.examples.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Whois {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {		
		String website;
		if (0 == args.length) {
			System.out.println("杈揿叆缃戝潃");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			website = br.readLine();
		} else {
			website = args[0];
		}
		
		Socket s = null;
		s = new Socket("internic.net",43);
		OutputStream os = s.getOutputStream();
		InputStream is = s.getInputStream();
		os.write(website.getBytes());
		int c;
		while (-1 != (c = is.read())) {
			System.out.println((char)c);
		}
		s.close();
	}
}
