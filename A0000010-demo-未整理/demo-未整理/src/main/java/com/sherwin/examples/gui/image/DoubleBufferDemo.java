package com.sherwin.examples.gui.image;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * 演示双缓冲技术的效果
 * 
 * @author Administrator
 * 
 */
public class DoubleBufferDemo extends Applet {
	int gap = 3;
	int mx, my;
	boolean useDoubleBuffer = true;
	boolean slowDown = true;
	Image buffer = null;
	int w, h;

	public void init() {
		setBackground(Color.BLACK);
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				mx = me.getX();
				my = me.getY();
				useDoubleBuffer = true;
				repaint();
			}

			public void mouseMoved(MouseEvent me) {
				mx = me.getX();
				my = me.getY();
				useDoubleBuffer = false;
				repaint();
			}
		});
	}

	public void paint(Graphics g) {
		Graphics screengc = null;

		// 如果使用双缓冲，则使用内存图像的graphics来画图
		if (useDoubleBuffer) {
			// 在内存中创建一个屏幕大小的图像
			Dimension d = getSize();
			w = d.width;
			h = d.height;
			buffer = createImage(w, h);

			// 用内存图像的graphics来画图
			screengc = g;
			g = buffer.getGraphics();
		}

		// 以下是画图过程，如果直接在屏幕的graphics上画，用户会看到一个很快的画图过程，就导致闪烁
		g.setColor(Color.blue);
		sleep();
		g.fillRect(0, 0, w, h);
		sleep();

		g.setColor(Color.red);
		sleep();
		for (int i = 0; i < w; i += gap) {
			g.drawLine(i, 0, w - i, h);
			sleep();
		}

		for (int i = 0; i < h; i += gap) {
			g.drawLine(0, i, w, h - i);
			sleep();
		}

		g.setColor(Color.black);
		sleep();
		g.drawString("Press mouse button to double buffer", 10, h / 2);
		sleep();

		g.setColor(Color.yellow);
		sleep();
		g.fillOval(mx - gap, my - gap, gap * 2 + 1, gap * 2 + 1);
		sleep();

		// 如果使用双缓冲，则把画好的内存图像一次性画到屏幕上
		if (useDoubleBuffer) {
			screengc.drawImage(buffer, 0, 0, null);
		}
	}

	/* 这里override默认的update方法很重要，因为默认的update方法里会先设置背景色再调用paint
	 * 如果update设置的背景色和paint中的背景色不一致还是会有闪烁
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * 如果想看清楚过程，请打开减速开关
	 */
	private void sleep() {
		if (slowDown) {
			for (int i = 0; i < 1000000; i++)
				;
		}
	}
}
