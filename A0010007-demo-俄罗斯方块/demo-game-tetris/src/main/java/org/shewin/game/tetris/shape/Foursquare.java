package org.shewin.game.tetris.shape;

import java.awt.Color;
import java.awt.Graphics;

public class Foursquare extends Shape {

	public Foursquare(int x, int y, int atomSize) {
		super(x, y, atomSize);
	}
	
	public Foursquare(int x, int y, int atomSize, Color color) {
		super(x, y, atomSize, color);
	}
	
	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		
		g.setColor(color);
		
		g.drawRect(x*atomSize, y*atomSize, 2*atomSize, 2*atomSize);
		
		g.setColor(c);
	}
}
