package com.sherwin.examples.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * 一个基本的窗口
 */
public class FontAwtGui extends Frame {
	
	int x = 0, y = 0;
	
	int i = 0;
	
	Font[] fonts = null;
	
	public FontAwtGui() {
		this.setBackground(Color.black);
		this.setForeground(Color.green);
		
		fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {			
				repaint();
			}
		});
	}
	
	public void paint(Graphics g) {
		System.out.println("paint");
		x = 0;
		y = 0;
		Random r = new Random(System.currentTimeMillis());
		for(int i=0; i<fonts.length; i++) {
			//这句会引起界面不断repaint
//			this.setFont(fonts[i++]);			
			int k = r.nextInt(3);
			switch (k) {
			case 1:
				this.nextLine(fonts[i].getName(), g);
				break;
			case 2:
				this.sameLine(fonts[i].getName(), g);
				break;
			}
		}

	}
	
	void nextLine(String s, Graphics g) {
		//使用FontMetrics得到输出字体的信息，以便各行文字不覆盖
		FontMetrics fm = g.getFontMetrics();
		y += fm.getHeight();
		x = 0;
		g.drawString(s, x, y);
		x = fm.stringWidth(s);
	}
	
	void sameLine(String s, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		g.drawString(s, x, y);
		x += fm.stringWidth(s);		
	}
	
	public static void main(String[] args) {
		FontAwtGui f = new FontAwtGui();
		f.setTitle("我的窗口");
		f.setSize(640,480);
		f.setVisible(true);
	}

}
