/**
 * 
 */
package com.sherwin.examples.rmi.basic;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 生成密码的服务接口
 * @author suirongw
 *
 */
public interface GenPasswdService extends Remote {
	
	String generatePassword(String seed) throws RemoteException;

}
