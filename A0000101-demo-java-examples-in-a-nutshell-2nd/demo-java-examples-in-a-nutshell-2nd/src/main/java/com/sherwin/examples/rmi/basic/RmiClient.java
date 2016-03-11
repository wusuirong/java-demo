/**
 * 
 */
package com.sherwin.examples.rmi.basic;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author suirongw
 *
 */
public class RmiClient {

	/**
	 * @param args
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String uri = "rmi://localhost/getPwd";
		GenPasswdService service = (GenPasswdService) Naming.lookup(uri);
		String passwd = service.generatePassword("hello");
		System.out.println(passwd);
	}

}
