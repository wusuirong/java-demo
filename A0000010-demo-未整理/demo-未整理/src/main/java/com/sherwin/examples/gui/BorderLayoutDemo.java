package com.sherwin.examples.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BorderLayoutDemo extends Frame {
	
	public BorderLayoutDemo() {
		this.setLayout(new BorderLayout());
		
		Button btn = null;
		
		btn = new Button("东");
		this.add(btn,BorderLayout.EAST);
		
		btn = new Button("南");
		this.add(btn,BorderLayout.SOUTH);
		
		btn = new Button("西");
		this.add(btn,BorderLayout.WEST);
		
		btn = new Button("北");
		this.add(btn,BorderLayout.NORTH);
		
		btn = new Button("中");
		this.add(btn,BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		BorderLayoutDemo f = new BorderLayoutDemo();
		f.setTitle("框布局演示");
		f.pack();
		f.setVisible(true);
	}

}
