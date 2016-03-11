package org.sherwin.game.tankwar.client.copy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TankJoinMsg {
	
	private Tank tank;
	
	public TankJoinMsg(Tank tank) {
		this.tank = tank;
	}
	
	public void send(DatagramSocket ds, String ip, int port) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		try {
			dos.writeInt(tank.getId());
			dos.writeInt(tank.getX());
			dos.writeInt(tank.getY());
			dos.writeBoolean(tank.isFriend());
			dos.writeInt(tank.getDirection().ordinal());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		byte[] buf = baos.toByteArray();
		DatagramPacket dp;
		try {
			dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(ip, port));
			ds.send(dp);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Tank receive(DatagramPacket dp) {
		ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
		DataInputStream dis = new DataInputStream(bais);
		
		try {
			int id = dis.readInt();
			int x = dis.readInt();
			int y = dis.readInt();
			boolean friend = dis.readBoolean();
			int direction = dis.readInt();
			
			Tank tank = new Tank(x, y, 0, 0, 0, friend, 0, null);
			tank.setId(id);
			tank.setDirection(Direction.values()[direction]);
			return tank;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
