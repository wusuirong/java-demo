package com.sherwin.examples.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GridLayoutDemo extends Frame {
	
	public GridLayoutDemo() {
		this.setLayout(new GridLayout(3,2));//
		
		Button btn = null;		
		for (int i=0; i<6; i++) {
			btn = new Button("第" + i + "个按钮");
			this.add(btn);
		}
	}
	
	public static void main(String[] args) {
		GridLayoutDemo f = new GridLayoutDemo();
		f.setTitle("网格布局演示");
		f.pack();
		f.setVisible(true);
	}

}
