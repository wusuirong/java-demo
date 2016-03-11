package org.shewin.game.tetris.model.shape;

import org.shewin.game.tetris.model.GameArea;


public class TShape extends Shape {
	
	private int[][] upShape = new int[][]{{0,0,0,0},{1,1,1,0},{0,1,0,0},{0,0,0,0}};
	private int[][] downShape = new int[][]{{0,1,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}};
	private int[][] leftShape = new int[][]{{0,1,0,0},{1,1,0,0},{0,1,0,0},{0,0,0,0}};
	private int[][] rightShape = new int[][]{{0,1,0,0},{0,1,1,0},{0,1,0,0},{0,0,0,0}};

	public TShape(int x, int y) {
		super(x, y);
		shape = upShape;
	}

	@Override
	public GameArea clockwiseRotate(GameArea gameArea) {
		switch (currentFace) {
		case UP:
			shape = rightShape;
			break;
		case DOWN:
			shape = leftShape;
			break;
		case LEFT:
			shape = upShape;
			break;
		case RIGHT:
			shape = downShape;
			break;
		}
		return gameArea;
	}

	@Override
	public GameArea anticlockwiseRotate(GameArea gameArea) {
		switch (currentFace) {
		case UP:
			shape = leftShape;
			break;
		case DOWN:
			shape = rightShape;
			break;
		case LEFT:
			shape = downShape;
			break;
		case RIGHT:
			shape = upShape;
			break;
		}
		return gameArea;
	}

	@Override
	public GameArea moveLeft(GameArea gameArea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameArea moveRight(GameArea gameArea) {
		// TODO Auto-generated method stub
		return null;
	}
}
