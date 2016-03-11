package com.sherwin.examples.rmi.client;

import java.rmi.Naming;

import com.sherwin.examples.rmi.server.AddServerIntf;

public class AddClient {
	public static void main(String args[]) {
		String url = "127.0.0.1";
		int num1 = 25;
		int num2 = 10;
		try {
			String addServerURL = "rmi://" + url + "/AddServer";
			AddServerIntf addServerIntf = (AddServerIntf) Naming.lookup(addServerURL);
			System.out.println("The first number is: " + num1);
			double d1 = Double.valueOf(num1).doubleValue();
			System.out.println("The second number is: " + num2);

			double d2 = Double.valueOf(num2).doubleValue();
			System.out.println("The sum is: " + addServerIntf.add(d1, d2));
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}