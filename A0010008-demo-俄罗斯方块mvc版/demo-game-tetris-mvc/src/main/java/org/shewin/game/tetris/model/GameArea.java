package org.shewin.game.tetris.model;

import org.shewin.game.tetris.model.shape.Shape;

public interface GameArea {

	public int[][] getArea();
	
	public Shape getShape();
}
