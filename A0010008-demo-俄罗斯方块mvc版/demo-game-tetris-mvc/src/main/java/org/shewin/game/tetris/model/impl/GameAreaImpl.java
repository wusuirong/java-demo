package org.shewin.game.tetris.model.impl;

import org.shewin.game.tetris.model.GameArea;
import org.shewin.game.tetris.model.GameObjectController;
import org.shewin.game.tetris.model.ShapeCollisionListener;
import org.shewin.game.tetris.model.ShapeCollisionSubject;
import org.shewin.game.tetris.model.shape.Shape;

public class GameAreaImpl implements GameArea, GameObjectController, ShapeCollisionSubject {
	/**
	 * 用二维数组表示游戏区域
	 */
	private int[][] area;
	private int wight;
	private int height;
	
	/**
	 * 当前形状
	 */
	private Shape shape;
	
	public GameAreaImpl(int wight, int height, int elementSize) {
		this.wight = wight;
		this.height = height;
		area = new int[wight][height];
	}
	
	/**
	 * 当前方块左移
	 * @return
	 */
	public boolean moveShapeToLeft() {
		return false;
	}
	
	/**
	 * 当前方块右移
	 * @return
	 */
	public boolean moveShapeToRight() {
		return false;
	}
	
	/**
	 * 旋转当前方块
	 * @return
	 */
	public boolean rotateShape() {
		return false;
	}

	public void deleteShape() {
		shape = null;
	}

	public boolean hasShape() {
		// TODO Auto-generated method stub
		return (null != shape);
	}

	public boolean isShapeHitBottom() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isShapeHitObject() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean moveShapeDown() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setShape(Shape shape) {
		// TODO Auto-generated method stub
		return false;
	}

	public void registerListener(ShapeCollisionListener shapeCollisionListener) {
		// TODO Auto-generated method stub
		
	}

	public void removeListener(ShapeCollisionListener shapeCollisionListener) {
		// TODO Auto-generated method stub
		
	}

	public int[][] getArea() {
		return area;
	}

	public Shape getShape() {
		return shape;
	}

}
