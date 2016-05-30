/**
 * 
 */
package com.sherwin.examples.rmi.basic;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * @author suirongw
 *
 */
public class RmiServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		String uri = args[0];
//		LocateRegistry.createRegistry(12312);
		
		GenPasswdService service = new GenPasswdServiceImpl();
		Naming.rebind(uri, service);
		System.out.println("rmi server start up at " + uri);
	}
}
