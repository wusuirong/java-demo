/**
 * 
 */
package com.sherwin.examples.rmi.basic;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * @author suirongw
 *
 */
public class RmiServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		GenPasswdService service = new GenPasswdServiceImpl();
		Naming.rebind("getPwd", service);
	}
}
