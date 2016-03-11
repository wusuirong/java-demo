package org.sherwin.game.tankwar.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.sherwin.game.tankwar.server.TankWarServer;

public class NetClient {
	
	private Socket s;
	
	private static int UDP_PORT = 20000;
	
	private int udpPort;
	
	private TankWarClient tankWarClient;
	
	DatagramSocket ds = null;
	
	public NetClient(TankWarClient tankWarClient) {
		udpPort = UDP_PORT++;
		this.tankWarClient = tankWarClient;
		try {
			ds = new DatagramSocket(udpPort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new ReceiveThread()).start();
	}

	public void connect(String ip, int port) {
		try {
			s = new Socket(ip, port);
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeInt(udpPort);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt();
			tankWarClient.getMyTanks().get(0).setId(id);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		TankJoinMsg tankJoinMsg = new TankJoinMsg(tankWarClient.getMyTanks().get(0));
		send(tankJoinMsg);
	}
	
	public void send(TankJoinMsg tankJoinMsg) {
		tankJoinMsg.send(ds, "127.0.0.1", TankWarServer.UDP_PORT);
	}
	
	private class ReceiveThread implements Runnable {

		public void run() {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			while (null != ds) {
				try {
					ds.receive(dp);
					System.out.println("receive a packet from server.");
					Tank tank = TankJoinMsg.receive(dp);
System.out.println("id: " + tank.getId() + ", x: " + tank.getX() + ", y: " + tank.getY() + ", friend: " + tank.isFriend() + ", direction: " + tank.getDirection());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
