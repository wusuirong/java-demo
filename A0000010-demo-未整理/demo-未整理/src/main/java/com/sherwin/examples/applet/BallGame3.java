package com.sherwin.examples.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/*
 * 增加一个球拍
 */
public class BallGame3 extends BallGame {

	Ball[] balls;
	
	Racket racket;
	
	int count;

	public void init() {
		count = 10;
		balls = new Ball[count];
		for (int i=0; i<count; i++) {
			Color color = null;
			switch (i%4) {
			case 0:
				color = Color.red;
				break;
			case 1:
				color = Color.yellow;
				break;
			case 2:
				color = Color.blue;
				break;
			case 3:
				color = Color.green;
				break;
			}
			Ball ball = new Ball(this, 15*i, 15, 15, i, color);
			balls[i] = ball;
		}
		
		racket = new Racket(this, 0, this.getHeight() - 20, 50, 10, Color.orange);
		
		this.addMouseMotionListener(racket);
	}
	
	public void start() {
		for (int i=0; i<count; i++) {
			Thread ballThread = new Thread(balls[i]);
			ballThread.start();
		}
	}
	
	public void stop() {
		
	}
	
	public void destroy() {
		
	}
	
	public void paint(Graphics g) {
		for (int i=0; i<count; i++) {
			g.setColor(balls[i].color);
			g.fillOval(balls[i].x, balls[i].y, balls[i].r, balls[i].r);
		}
		g.setColor(racket.color);
		g.fillRect(racket.x, racket.y, racket.width, racket.height);
	}
	
	/*
	 * 如果覆盖这个方法，且直接调用paint，则会发现因为没清除界面，导致画的球成为一条粗线
	 */
//	public void update(Graphics g) {
//		/*g.clearRect(0, 0, this.getWidth(), this.getHeight());*/
//		paint(g);
//	}
}