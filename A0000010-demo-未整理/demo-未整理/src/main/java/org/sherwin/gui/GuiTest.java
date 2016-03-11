package org.sherwin.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		JPanel panel1 = new JPanel();
		frame.setContentPane(panel1);
		frame.setSize(300,400);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		panel1.setLayout(new BorderLayout(50,100));
		
		JButton button1 = new JButton("hello1");
		panel1.add(button1,BorderLayout.WEST);
		JTextField textField1 = new JTextField(10);
		panel1.add(textField1,BorderLayout.CENTER);
		
		JButton button2 = new JButton("hello2");
		panel1.add(button2,BorderLayout.NORTH);
		JTextField textField2 = new JTextField(10);
		panel1.add(textField2,BorderLayout.SOUTH);
		
		JButton button3 = new JButton("hello3");
		panel1.add(button3,BorderLayout.EAST);
		JTextField textField3 = new JTextField(10);
		panel1.add(textField3,BorderLayout.SOUTH);
		
		
		
		JPanel panel2 = new JPanel();
		panel1.add(panel2);
		
		JPanel panel3 = new JPanel();
		
		
		frame.show();
	}
}

class MyMouseListener implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
}


