package org.shewin.game.tetris.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public abstract class Shape {
	protected int x, y;
	protected int atomSize;
	protected Color color = Color.red;
	protected Direction direction = Direction.UP;

	public Shape(int x, int y, int atomSize) {
		this.x = x;
		this.y = y;
		this.atomSize = atomSize;
	}
	
	public Shape(int x, int y, int atomSize, Color color) {
		this(x, y, atomSize);
		this.color = color;
	}

	public abstract void draw(Graphics g);

}
