package com.sherwin.examples.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 演示如何画画
 */
public class AnimationApplet extends Applet implements Runnable {
	
	private String msg = "Welcome to Applet World.";
	private Thread t = null;
	private boolean running = false;

	public void init() {
		System.out.println("init");
		this.setBackground(Color.black);
		this.setForeground(Color.green);
	}
	
	public void start() {
		System.out.println("start");
		t = new Thread(this);
		running = true;
		t.start();
	}
	
	public void stop() {
		System.out.println("stop");
		running = false;
		t = null;
	}
	
	public void destroy() {
		System.out.println("destroy");
	}

	public void paint(Graphics g) {
		System.out.println("paint");
		g.drawString(msg, 10, 30);
		this.showStatus(msg);
	}

	public void run() {
		char ch;
		while (true) {
			if (running) {
				ch = msg.charAt(0);
				msg = msg.substring(1, msg.length()) + ch;
				this.repaint();
				//repaint是异步的，调用repaint后尽快把控制权交还AWT环境来刷新
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}
	}
}
