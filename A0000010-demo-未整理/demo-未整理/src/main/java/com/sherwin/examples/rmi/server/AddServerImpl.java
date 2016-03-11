package com.sherwin.examples.rmi.server;

import java.rmi.*;
import java.rmi.server.*;

/**
 * UnicastRemoteObject是通过JRMP实现远程通信的类，直接继承它就可以基于JRMP协议通信了
 * @author Administrator
 *
 */
public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {

	public AddServerImpl() throws RemoteException {
	}

	public double add(double d1, double d2) throws RemoteException {
		return d1 + d2;
	}
}