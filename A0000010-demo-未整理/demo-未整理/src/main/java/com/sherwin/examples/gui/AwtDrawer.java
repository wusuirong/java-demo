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
 * 演示一些画图方法
 */
public class AwtDrawer extends Frame {
	
	int x = 0, y = 0;
	
	final int length = 30;
	
	public AwtDrawer() {
		this.setBackground(Color.black);
		this.setForeground(Color.green);
		this.setSize(406,306);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});

		
		//获得焦点以便接收键盘事件
		this.requestFocus();
	}
	
	/*
	 * Graphics组件其实是窗口类里的，不传入也能得到
	 */
	public void paint(Graphics g) {
		g.drawLine(60, 60, 90, 60);
		g.drawRoundRect(100, 100, 100, 50, 20, 30);
		g.fillRect(30, 80, length, length);
		g.setXORMode(Color.blue);
		g.fillRect(50, 100, length, length);
	}
	
	public static void main(String[] args) {
		AwtDrawer f = new AwtDrawer();
		f.setTitle("我的窗口");
		f.setVisible(true);
	}

}
