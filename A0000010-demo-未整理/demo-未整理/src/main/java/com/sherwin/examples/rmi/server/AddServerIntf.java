package com.sherwin.examples.rmi.server;

import java.rmi.*;

/**
 * 服务端远程接口声明，只有这里声明的接口方法才能被远程调用
 * @author Administrator
 *
 */
public interface AddServerIntf extends Remote {
	double add(double d1, double d2) throws RemoteException;
}