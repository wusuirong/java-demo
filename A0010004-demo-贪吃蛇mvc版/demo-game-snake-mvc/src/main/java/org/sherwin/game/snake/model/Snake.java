package org.sherwin.game.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author suirongw
 *
 */
public class Snake {

	private List<Node> nodes;
	
	public Snake(int headX, int headY) {
		nodes = new ArrayList<Node>();
		Node node = new Node(headX, headY, Direction.RIGHT);
		nodes.add(node);
	}
	
	public void reset(int headX, int headY) {
		nodes.clear();
		Node node = new Node(headX, headY, Direction.RIGHT);
		nodes.add(node);
	}
	
	/**
	 * 改变蛇头的当前方向
	 * @param direction
	 */
	public void changeDirection(Direction direction) {
		Node head = nodes.get(0);
		switch (direction) {
		case DOWN:
			if (head.getDirection().equals(Direction.UP)) {
				return;
			}
			break;
		case LEFT:
			if (head.getDirection().equals(Direction.RIGHT)) {
				return;
			}
			break;
		case RIGHT:
			if (head.getDirection().equals(Direction.LEFT)) {
				return;
			}
			break;
		case UP:
			if (head.getDirection().equals(Direction.DOWN)) {
				return;
			}
			break;
		}
		head.setDirection(direction);
	}
	
	/**
	 * 让蛇朝当前方向移动一步
	 */
	public void move() {
		for (int i=nodes.size()-1; i>0; i--) {
			Node node = nodes.get(i);
			Node preNode = nodes.get(i-1);
			node.setX(preNode.getX());
			node.setY(preNode.getY());
			node.setDirection(preNode.getDirection());
		}
		Node head = nodes.get(0);
		switch (head.getDirection()) {
		case UP:
			head.setY(head.getY()-1);
			break;
		case DOWN:
			head.setY(head.getY()+1);
			break;
		case LEFT:
			head.setX(head.getX()-1);
			break;
		case RIGHT:
			head.setX(head.getX()+1);
			break;
		}
	}
	
	/**
	 * 让蛇增加指定长度
	 * @param length
	 */
	public void growUp(int length) {
		Node lastNode = nodes.get(nodes.size()-1);
		int x = lastNode.getX();
		int y = lastNode.getY();
		Direction direction = lastNode.getDirection();
		for (int i=0; i<length; i++) {
			switch (direction) {
			case UP:
				y++;
				break;
			case DOWN:
				y--;
				break;
			case LEFT:
				x++;
				break;
			case RIGHT:
				x--;
				break;
			}
			Node node = new Node(x, y, direction);
			nodes.add(node);
		}
	}
	
	/**
	 * 是否碰到石头
	 * @param rocks
	 * @return
	 */
	public boolean hitRock(List<Rock> rocks) {
		for (int i=0; i<rocks.size(); i++) {
			Rock rock = rocks.get(i);
			for (int j=0; j<nodes.size(); j++) {
				Node node = nodes.get(j);
				if (node.getX() == rock.getX() 
						&& node.getY() == rock.getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Food eatFood(List<Food> foods) {
		for (int i=0; i<foods.size(); i++) {
			Food food = foods.get(i);
			for (int j=0; j<nodes.size(); j++) {
				Node node = nodes.get(j);
				if (node.getX() == food.getX() 
						&& node.getY() == food.getY()) {
					return food;
				}
			}
		}
		return null;
	}
	
	/**
	 * 是否碰到自己
	 * @return
	 */
	public boolean hitMyself() {
		for (int i=0; i<nodes.size()-1; i++) {
			for (int j=i+1; j<nodes.size(); j++) {
				if (nodes.get(i).getX() == nodes.get(j).getX() 
						&& nodes.get(i).getY() == nodes.get(j).getY()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否出界
	 * @param ground
	 * @return
	 */
	public boolean outbound(Ground ground) {
		int headX = nodes.get(0).getX();
		int headY = nodes.get(0).getY();
		switch (nodes.get(0).getDirection()) {
		case UP:
			if (headY >= 0) {
				return false;
			}
			break;
		case DOWN:
			if (headY < ground.getHeight()) {
				return false;
			}
			break;
		case LEFT:
			if (headX >= 0) {
				return false;
			}
			break;
		case RIGHT:
			if (headX < ground.getWidth()) {
				return false;
			}
			break;
		}
		return true;
	}
	
	/**
	 * @return the nodes
	 */
	public List<Node> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public class Node {
		private int x,y;
		private Direction direction;
		
		public Node(int x, int y, Direction direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

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

		/**
		 * @return the direction
		 */
		public Direction getDirection() {
			return direction;
		}

		/**
		 * @param direction the direction to set
		 */
		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
}