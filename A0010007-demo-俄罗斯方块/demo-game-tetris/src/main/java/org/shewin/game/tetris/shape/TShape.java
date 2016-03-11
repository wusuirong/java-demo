package org.shewin.game.tetris.shape;

import java.awt.Color;
import java.awt.Graphics;

public class TShape extends Shape {

	public TShape(int x, int y, int atomSize) {
		super(x, y, atomSize);
	}
	
	public TShape(int x, int y, int atomSize, Color color) {
		super(x, y, atomSize, color);
	}
	
	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		
		g.setColor(color);
		
		switch (direction) {
		case UP:
			break;
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}
		g.drawRect(x*atomSize, y*atomSize, 2*atomSize, 2*atomSize);
		
		g.setColor(c);
	}
}
