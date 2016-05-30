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
		String uri = args[0]; //"rmi://localhost:20000/GetPwd";
		System.out.println("rmi client lookup " + uri);
		
		GenPasswdService service = (GenPasswdService) Naming.lookup(uri);
		String passwd = service.generatePassword("hello");
		System.out.println(passwd);
	}

}
