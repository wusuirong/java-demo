package org.sherwin.game.chat.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient extends Frame {
	
	private TextField inputField;
	private TextArea messageArea;
	
	private static final String SERVER_IP = "192.168.1.5";
	private static final int SERVER_TCP_PORT = 8888;
	private Socket s;

	public ChatClient() {
		this.setLayout(new BorderLayout());
		
		inputField = this.initInputField();
		this.add(inputField, BorderLayout.SOUTH);
		
		messageArea = new TextArea();
		this.add(messageArea, BorderLayout.CENTER);
		
		this.addWindowListener(new WindowAdapter() {

			/* (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.setTitle("聊天客户端");
		this.setLocation(30, 30);
		this.setSize(new Dimension(300, 300));
		
		this.pack();
		this.setVisible(true);
		
		initSocket();
	}
	
	private boolean initSocket() {
		try {
			s = new Socket(SERVER_IP, SERVER_TCP_PORT);
			System.out.println("connected to server."
					+ "\r\n" + "getInetAddress " + s.getInetAddress()
					+ "\r\n" + "getLocalPort " + s.getLocalPort()
					+ "\r\n" + "getPort " + s.getPort()
					+ "\r\n" + "getLocalAddress " + s.getLocalAddress()
					+ "\r\n" + "getLocalSocketAddress " + s.getLocalSocketAddress()
					+ "\r\n" + "getRemoteSocketAddress " + s.getRemoteSocketAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private TextField initInputField() {
		TextField tf = new TextField();
		
		tf.addActionListener(new EnterActionListener());
		return tf;
	}
	
	private class EnterActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String input = inputField.getText();
			messageArea.setText(messageArea.getText() + "\r\n" + input);
			
			
			try {
				OutputStream os = s.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF(inputField.getText());
				dos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			inputField.setText("");
		}
		
	}
}
