package com.sherwin.examples.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 演示applet生命周期
 */
public class BasicApplet extends Applet {
	
	private String msg = "";

	public void init() {
		System.out.println("init");
		this.setBackground(Color.black);
		this.setForeground(Color.green);
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
	
	/*
	 * 看jdk源码可知调用顺序为repaint->update->paint
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */	
	public void repaint(Graphics g) {
		System.out.println("repaint");
	}
	
	public void update(Graphics g) {
		System.out.println("update");
	}
	
	public void paint(Graphics g) {
		System.out.println("paint");
		msg += "painted ";
		g.drawString(msg, 10, 30);
	}
}
