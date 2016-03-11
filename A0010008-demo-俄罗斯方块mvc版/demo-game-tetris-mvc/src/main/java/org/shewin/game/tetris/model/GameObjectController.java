package org.shewin.game.tetris.model;

import org.shewin.game.tetris.model.shape.Shape;

/**
 * 游戏物体控制器，提供控制形状，游戏区的指令
 * @author Administrator
 *
 */
public interface GameObjectController {
		
	/**
	 * 往游戏区放入新形状
	 * true 成功放入形状
	 * false 游戏区满了，放不下形状
	 * @return
	 */
	public boolean setShape(Shape shape);
	
	/**
	 * 游戏区里是否有活动形状
	 * @return
	 */
	public boolean hasShape();
	
	/**
	 * 形状是否碰到底部了
	 * @return
	 */
	public boolean isShapeHitBottom();
	
//	/**
//	 * 形状是否碰到墙壁或建筑物
//	 * @return
//	 */
//	public boolean isShapeHitObject();
	
	/**
	 * 删除当前活动形状，并通知观察者
	 */
	public void deleteShape();
	
	/**
	 * 当前方块左移
	 * @return
	 */
	public boolean moveShapeToLeft();
	
	/**
	 * 当前方块右移
	 * @return
	 */
	public boolean moveShapeToRight();
	
	/**
	 * 当前方块下降
	 * @return
	 */
	public boolean moveShapeDown();
	
	/**
	 * 旋转当前方块
	 * @return
	 */
	public boolean rotateShape();	
	
}
