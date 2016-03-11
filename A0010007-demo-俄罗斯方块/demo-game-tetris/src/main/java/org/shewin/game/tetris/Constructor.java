package org.shewin.game.tetris;

import java.util.ArrayList;
import java.util.List;

import org.shewin.game.tetris.panel.ConstructionPanel;
import org.shewin.game.tetris.shape.Direction;
import org.shewin.game.tetris.shape.Shape;

public class Constructor {

	private ConstructionPanel constructionPanel;
	
	private List<Shape> shapes = new ArrayList<Shape>();
	
	/**
	 * 改变当前形状的方向
	 * @param direction
	 */
	public void changeShapeDirection(Direction direction) {
		
	}
	
	/**
	 * 移动当前形状
	 */
	public void moveShape(Direction direction) {
		
	}
	
	/**
	 * 检测是否有建好的行
	 */
	public void detectBuiltLine() {
		
	}
	
	/**
	 * 检测是否有形状到顶
	 * @return
	 */
	public boolean detectOverheadShape() {
		return false;
	}
}
