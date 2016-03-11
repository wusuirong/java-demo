package org.shewin.game.tetris.model.shape;

import org.shewin.game.tetris.model.Direction;
import org.shewin.game.tetris.model.GameArea;


/**
 * @author Administrator
 *
 */
public abstract class Shape {
	/**
	 * 在游戏区中的x坐标
	 */
	protected int x;
	
	/**
	 * 在游戏区中的y坐标
	 */
	protected int y;
	
	/**
	 * 当前朝向，方便旋转使用
	 */
	protected Direction currentFace;
	
	/**
	 * 形状的数组描述
	 */
	protected int[][] shape = new int[4][4];

	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
		this.currentFace = Direction.UP;
	}


	/**
	 * 顺时针旋转形状
	 * @return
	 */
	abstract public GameArea clockwiseRotate(GameArea gameArea);
	
	/**
	 * 逆时针旋转形状
	 * @return
	 */
	abstract public GameArea anticlockwiseRotate(GameArea gameArea);
	
	/**
	 * 形状往左移动
	 * @param gameArea
	 * @return
	 */
	abstract public GameArea moveLeft(GameArea gameArea);
	
	/**
	 * 形状往右移动
	 * @param gameArea
	 * @return
	 */
	abstract public GameArea moveRight(GameArea gameArea);

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
