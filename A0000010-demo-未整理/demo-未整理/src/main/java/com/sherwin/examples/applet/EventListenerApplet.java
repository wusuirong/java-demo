package com.sherwin.examples.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 * 演示事件处理
 */
public class EventListenerApplet extends Applet implements MouseListener {

	int x = 0, y = 0;
	
	public void init() {
		System.out.println("init");
		this.setBackground(Color.black);
		this.setForeground(Color.green);
		this.addMouseListener(this);
	}
	
	public void start() {
		System.out.println("start");
	}
	
	public void stop() {
		System.out.println("stop");
	}
	
	public void destroy() {
		System.out.println("destroy");
	}

	public void paint(Graphics g) {
		System.out.println("paint");
		g.drawRect(x, y, 10, 10);
	}

	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		this.repaint();
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
