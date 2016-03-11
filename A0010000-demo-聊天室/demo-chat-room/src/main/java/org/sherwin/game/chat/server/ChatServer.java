package org.sherwin.game.chat.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	
	private ServerSocket ss;
	private static final int TCP_PORT = 8888;
	
	private List<Socket> sockets = new ArrayList<Socket>();

	public void run() {
		try {
			ss = new ServerSocket(TCP_PORT);
			System.out.println("Server listening" 
					+ "\r\n" + "getInetAddress " + ss.getInetAddress() 
					+ "\r\n" + "getLocalPort " + ss.getLocalPort()
					+ "\r\n" + "getLocalSocketAddress " + ss.getLocalSocketAddress());
			
			while (true) {
				Socket s = ss.accept();
				System.out.println("A client connected."
						+ "\r\n" + "getInetAddress " + s.getInetAddress()
						+ "\r\n" + "getLocalPort " + s.getLocalPort()
						+ "\r\n" + "getPort " + s.getPort()
						+ "\r\n" + "getLocalAddress " + s.getLocalAddress()
						+ "\r\n" + "getLocalSocketAddress " + s.getLocalSocketAddress()
						+ "\r\n" + "getRemoteSocketAddress " + s.getRemoteSocketAddress());
				
				sockets.add(s);
				
				InputStream is = s.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				String str = null;
				while (null != (str=dis.readUTF())) {
					System.out.println(str);
				}
			}
		} catch (IOException e) {
			System.out.println("socket init on port " + TCP_PORT + " failed. Maybe sth running on it.");
			e.printStackTrace();
			return;
		}
	}
}
