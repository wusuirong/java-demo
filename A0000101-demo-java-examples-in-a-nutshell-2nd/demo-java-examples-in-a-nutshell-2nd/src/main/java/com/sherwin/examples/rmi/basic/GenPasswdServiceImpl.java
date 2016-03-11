/**
 * 
 */
package com.sherwin.examples.rmi.basic;

import java.rmi.RemoteException;

/**
 * @author suirongw
 *
 */
public class GenPasswdServiceImpl implements GenPasswdService {

	/* (non-Javadoc)
	 * @see com.sherwin.examples.rmi.basic.GenPasswdService#generatePassword(java.lang.String)
	 */
	public String generatePassword(String seed) throws RemoteException {
		return seed + System.currentTimeMillis();
	}

}
