package org.shewin.game.tetris.model;

/**
 * 形状碰撞接口
 * @author Administrator
 *
 */
public interface ShapeCollisionSubject {
	
	public void registerListener(ShapeCollisionListener shapeCollisionListener);
	
	public void removeListener(ShapeCollisionListener shapeCollisionListener);
	
	
}
