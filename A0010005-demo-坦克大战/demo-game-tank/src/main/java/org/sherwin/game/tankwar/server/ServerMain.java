package org.sherwin.game.tankwar.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		new TankWarServer().start();
	}
	
}
