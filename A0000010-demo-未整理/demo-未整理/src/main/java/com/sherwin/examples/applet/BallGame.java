package com.sherwin.examples.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

/*
 * 主程序，负责画界面
 * 演示多线程画界面的方法
 */
public class BallGame extends Applet {
	
	Ball ball;
	Ball ball2;

	public void init() {
		ball = new Ball(this, 30, 30, 15, 3, Color.green);
		ball2 = new Ball(this, 40, 30, 30, 2, Color.red);
	}
	
	public void start() {
		Thread ballThread = new Thread(ball);
		ballThread.start();
		
		Thread ballThread2 = new Thread(ball2);
		ballThread2.start();
	}
	
	public void stop() {
		
	}
	
	public void destroy() {
		
	}
	
	public void paint(Graphics g) {
		g.setColor(ball.color);
		g.fillOval(ball.x, ball.y, ball.r, ball.r);
		
		g.setColor(ball2.color);
		g.fillOval(ball2.x, ball2.y, ball2.r, ball2.r);
	}
	
	/*
	 * 如果覆盖这个方法，且直接调用paint，则会发现因为没清除界面，导致画的球成为一条粗线
	 */
//	public void update(Graphics g) {
//		/*g.clearRect(0, 0, this.getWidth(), this.getHeight());*/
//		paint(g);
//	}
}
