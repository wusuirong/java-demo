package com.sherwin.examples.gui;

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

public class FlowLayoutDemo extends Frame {
	
	public FlowLayoutDemo() {
		this.setLayout(new FlowLayout());
		for (int i=0; i<3; i++) {
			Button btn = new Button("第" + i + "个按钮");
			this.add(btn);
		}
	}
	
	public static void main(String[] args) {
		FlowLayoutDemo f = new FlowLayoutDemo();
		f.setTitle("流布局演示");
		f.pack();
		f.setVisible(true);
	}

}
