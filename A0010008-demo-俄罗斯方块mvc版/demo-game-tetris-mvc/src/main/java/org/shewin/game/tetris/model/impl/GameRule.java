package org.shewin.game.tetris.model.impl;

import org.shewin.game.tetris.model.Direction;
import org.shewin.game.tetris.model.GameObjectController;

/**
 * 游戏规则，控制游戏如何进行
 * 就是游戏的算法
 * @author Administrator
 *
 */
public class GameRule implements Runnable {
	
	/**
	 * 游戏规则执行速度,1-10，越小越快
	 */
	protected int gameSpeed;
	
	/**
	 * 重力加速度，影响形状下落速度,1-10，越小越快
	 */
	protected int gravity;
	
	/**
	 * 规则是否执行
	 */
	protected boolean running;
	
	/**
	 * 游戏区
	 * 就是游戏的数据结构
	 */
	protected GameObjectController gameArea;
	
	/**
	 * 用户输入方向
	 */
	protected Direction userInputDirection;

	public void run() {
		int gravityCount = 0;
		while(true)
		{
			if(running) {
				executeRuleBeforeShapeDown();
				if (gravityCount == gravity) {
					moveDownShape();
					gravityCount = 0;
				}
				gravityCount++;
			}
			try {
				Thread.sleep(gameSpeed*100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void executeRuleBeforeShapeDown() {
		//游戏区没有活动方块
		if (!gameArea.hasShape()) {
			return;
		}
		
		//检测方块是否碰到底部建筑，用观察者模式
//		processShapeCollision();
		
		//用户没按方向键
		if (Direction.STOP == userInputDirection) {
			return;
		}
		
		//用户按了方向键
		switch (userInputDirection) {
		case UP:
			gameArea.rotateShape();
			break;
		case DOWN:
			gameArea.moveShapeDown();
			break;
		case LEFT:
			gameArea.moveShapeToLeft();
			break;			
		case RIGHT:
			gameArea.moveShapeToRight();
			break;
		}
		
		//方块碰到底部建筑了，用观察者模式
/*		if (processShapeCollision()) {
			return;
		}*/
	}
	
	protected void moveDownShape() {
		gameArea.moveShapeDown();
	}

	/**
	 * 判断是否有活动方块在游戏区
	 * @return
	 */
	protected boolean hasShapeInArea() {
		return gameArea.hasShape();
	}
	
	/**
	 * 方块碰撞处理
	 * 如果碰撞了，处理当前方块，加入新方块
	 * true 碰撞了
	 * false 没碰撞
	 * @return
	 */
	protected boolean processShapeCollision() {
		if (gameArea.isShapeHitBottom()) {
			//删除当前方块
			gameArea.deleteShape();
			gameArea.setShape(ShapeGenerator.getShape());
			return true;
		} else {
			return false;
		}
	}
}
