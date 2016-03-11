package com.sherwin.examples.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 演示事件适配器和内部类的使用
 */
public class EventAdapterApplet extends Applet {
	
	private int x = 0, y = 0;

	public void init() {
		System.out.println("init");
		this.setBackground(Color.black);
		this.setForeground(Color.green);

		/*
		 * 不知道Adapter有哪些方法时可先写Listener让eclipse提示
		 */
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP:
						showStatus("up");//匿名内部类能直接访问外部类的变量和方法
						if (y > 10) {
							y -= 10;
						}
						break;
					case KeyEvent.VK_DOWN:
						showStatus("down");
						if (y < getHeight() - 10) {
							y += 10;
						}
						break;
					case KeyEvent.VK_LEFT:
						showStatus("left");
						if (x > 10) {
							x -= 10;
						}
						break;
					case KeyEvent.VK_RIGHT:
						showStatus("right");
						if (x < getWidth() - 10) {
							x += 10;
						}
						break;
				}
				repaint();
			}
		});
		
		//获得焦点以便接收键盘事件
		this.requestFocus();
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
}
