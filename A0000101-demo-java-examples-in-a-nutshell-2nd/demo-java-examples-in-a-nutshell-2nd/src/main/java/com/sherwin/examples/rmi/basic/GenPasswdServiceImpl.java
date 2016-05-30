/**
 * 
 */
package com.sherwin.examples.rmi.basic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author suirongw
 *
 */
public class GenPasswdServiceImpl extends UnicastRemoteObject implements GenPasswdService {

	protected GenPasswdServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4368692227838833973L;

	/* (non-Javadoc)
	 * @see com.sherwin.examples.rmi.basic.GenPasswdService#generatePassword(java.lang.String)
	 */
	public String generatePassword(String seed) throws RemoteException {
		return seed + System.currentTimeMillis();
	}

}
