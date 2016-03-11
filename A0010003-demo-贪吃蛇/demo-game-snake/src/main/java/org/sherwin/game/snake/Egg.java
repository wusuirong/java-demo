package org.sherwin.game.snake;

import java.awt.Color;
import java.awt.Graphics;

public class Egg {

	int x,y, nodeSize;
	Color color = Color.GREEN;
	
	public Egg(int x, int y, int nodeSize) {
		this.x = x;
		this.y = y;
		this.nodeSize = nodeSize;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		
		g.setColor(color);
		
		g.fillRect(x*nodeSize, y*nodeSize, nodeSize, nodeSize);

		g.setColor(c);
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
}
