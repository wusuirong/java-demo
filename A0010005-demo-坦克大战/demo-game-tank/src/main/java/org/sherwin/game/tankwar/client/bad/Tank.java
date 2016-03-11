package org.sherwin.game.tankwar.client.bad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Tank {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, width, height);
		g.setColor(c);
	}
	
	
	public void keyPressed(KeyEvent e) {
		//每一次按键事件都改变x，y坐标，但屏幕刷新可能跟不上按键速度，如果多次按键事件发生后才有一次屏幕刷新，会导致坦克移动不连贯，时快时慢
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			y -= 5;
			break;
		case KeyEvent.VK_S:
			y += 5;
			break;
		case KeyEvent.VK_A:
			x -= 5;
			break;
		case KeyEvent.VK_D:
			x += 5;
			break;
		}
	}

	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
