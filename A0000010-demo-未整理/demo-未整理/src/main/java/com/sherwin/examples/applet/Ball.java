package com.sherwin.examples.applet;

import java.awt.Color;

/*
 * 一个来回弹跳的球
 */
public class Ball implements Runnable {
	
	public int x = 10;
	public int y = 10;
	public int r = 10;
	public int speed = 2;
	public Color color;
	BallGame ballGame;
	
	public Ball(BallGame ballGame, int x, int y, int r, int speed, Color color) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.speed = speed;
		this.color = color;
		this.ballGame = ballGame;
	}

	/*
	 * 它不负责画界面
	 * 它改变自身的参数，然后要求界面类重绘自己
	 */
	public void run() {
		while (true) {
			if (y+r > ballGame.getHeight()) {
				speed = -speed;
			} else if (y < 0) {
				speed = -speed;
			}
			y += speed;

			/*
			 * 要求界面重画
			 * 它将生成重画事件放入事件队列，awt的画画线程收到事件后调用applet的update，update里在清除界面后调用paint
			 * 这个update就是Container的update，Applet是Container子类
			 */
			ballGame.repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}