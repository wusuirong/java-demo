package com.sherwin.examples.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 一个基本的窗口
 */
public class BasicAwtGui extends Frame {
	
	int x = 0, y = 0;
	
	final int length = 30;
	
	public BasicAwtGui() {
		this.setBackground(Color.black);
		this.setForeground(Color.green);
		this.setSize(406,306);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});

		/*
		 * 不知道Adapter有哪些方法时可先写Listener让eclipse提示
		 */
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP:
						if (y > length) {
							y -= length;
						}
						break;
					case KeyEvent.VK_DOWN:
						if (y < getHeight() - length) {
							y += length;
						}
						break;
					case KeyEvent.VK_LEFT:
						if (x > length) {
							x -= length;
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (x < getWidth() - length) {
							x += length;
						}
						break;
				}
				repaint();
			}
		});
		
		//获得焦点以便接收键盘事件
		this.requestFocus();
	}
	
	/*
	 * Graphics组件其实是窗口类里的，不传入也能得到
	 */
	public void paint(Graphics g) {
		System.out.println("paint");
		Dimension d = this.getSize();
		g.drawRect(30, 30, d.width - 60, d.height - 60);
		g.fillRect(x, y, length, length);
	}
	
	public static void main(String[] args) {
		BasicAwtGui f = new BasicAwtGui();
		f.setTitle("我的窗口");
		f.setVisible(true);
	}

}
