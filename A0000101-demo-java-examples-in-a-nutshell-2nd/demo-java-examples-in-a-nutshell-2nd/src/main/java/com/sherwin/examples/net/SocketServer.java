package com.sherwin.examples.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(30000);
		Socket client = server.accept();
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter out = new PrintWriter(client.getOutputStream());
		while (true) {
			String str = in.readLine();
			System.out.println(str);
			out.println("true");
			out.flush();
		}
	}
}
