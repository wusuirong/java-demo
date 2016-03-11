package com.sherwin.examples.rmi.server;

import java.net.*;
import java.rmi.*;

/**
 * 把远程服务绑定到RMI注册表上
 * @author Administrator
 *
 */
public class AddServer {
	public static void main(String args[]) {
		try {
			AddServerImpl addServerImpl = new AddServerImpl();
			Naming.rebind("AddServer", addServerImpl);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}