package org.sherwin.game.tankwar.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.sherwin.game.tankwar.client.Tank;
import org.sherwin.game.tankwar.client.TankJoinMsg;

public class TankWarServer {

	public static final int TCP_PORT = 8888;

	public static final int UDP_PORT = 9000;

	private List<Client> clients = new ArrayList<Client>();

	private static int CLIENT_ID = 1;

	public void start() {
		ServerSocket ss;
		Socket s = null;
		
		new Thread(new UdpThread()).start();
		try {
			ss = new ServerSocket(TCP_PORT);
			while (true) {
				s = ss.accept();

				InputStream is = s.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				int udpPort = dis.readInt();
				Client c = new Client(s.getInetAddress().getHostAddress(), udpPort);
				clients.add(c);

				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(CLIENT_ID++);
	System.out.println("a client connected, udp port: " + udpPort);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s = null;
		}

	}

	private class Client {
		String ip;
		int udpPort;

		public Client(String ip, int udpPort) {
			this.ip = ip;
			this.udpPort = udpPort;
		}
	}
	
	private class UdpThread implements Runnable {

		public void run() {
			DatagramSocket ds;
			try {
				ds = new DatagramSocket(UDP_PORT);
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				while (null != ds) {					
					ds.receive(dp);

					Tank tank = TankJoinMsg.receive(dp);
		System.out.println("id: " + tank.getId() + ", x: " + tank.getX() + ", y: " + tank.getY() + ", friend: " + tank.isFriend() + ", direction: " + tank.getDirection());
		
					for (int i=0; i<clients.size(); i++) {
						Client c = clients.get(i);
						dp.setSocketAddress(new InetSocketAddress(c.ip, c.udpPort));
						ds.send(dp);
					}
				}				
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
