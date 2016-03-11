package org.sherwin.game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	private int nodeSize;
	private Yard yard;
	private List<SnakeNode> nodes = new ArrayList<SnakeNode>();
	private Color color = Color.RED;

	public Snake(int x, int y, int length, int nodeSize, Direction direction, Color color, Yard yard) {
		this.nodeSize = nodeSize;
		this.yard = yard;
		this.color = color;
		initNodes(x, y, length, direction);
	}
	
	private void initNodes(int x, int y, int length, Direction direction) {
		for (int i=0; i<length; i++) {
			if (x<0 || x>yard.WIDTH || y<0 || y>yard.HEIGHT) {
				break;
			}
			SnakeNode node = new SnakeNode(x, y, direction);
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
			nodes.add(node);
		}
	}
	
	public void changeDirection(Direction direction) {
		SnakeNode head = nodes.get(0);
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
	
	public void move() {
		for (int i=nodes.size()-1; i>0; i--) {
			SnakeNode node = nodes.get(i);
			SnakeNode preNode = nodes.get(i-1);
			node.setX(preNode.getX());
			node.setY(preNode.getY());
			node.setDirection(preNode.getDirection());
		}
		SnakeNode head = nodes.get(0);
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
	
	public boolean hitYard() {
		int headX = nodes.get(0).getX();
		int headY = nodes.get(0).getY();
		switch (nodes.get(0).getDirection()) {
		case UP:
			if (headY > 0) {
				return false;
			}
			break;
		case DOWN:
			if (headY < yard.ROWS-1) {
				return false;
			}
			break;
		case LEFT:
			if (headX > 0) {
				return false;
			}
			break;
		case RIGHT:
			if (headX < yard.COLS-1) {
				return false;
			}
			break;
		}
		return true;
	}
	
	public boolean hitMe() {
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
	
	public Egg eatEgg() {
		List<Egg> eggs = yard.getEggs();
		
		for (int i=0; i<eggs.size(); i++) {
			Egg egg = eggs.get(i);
			for (int j=0; j<nodes.size(); j++) {
				SnakeNode node = nodes.get(j);
				if (node.getX() == egg.getX() 
						&& node.getY() == egg.getY()) {
					return egg;
				}
			}
		}
		return null;
	}
	
	public Shit eatShit() {
		List<Shit> shits = yard.getShits();
		
		for (int i=0; i<shits.size(); i++) {
			Shit shit = shits.get(i);
			for (int j=0; j<nodes.size(); j++) {
				SnakeNode node = nodes.get(j);
				if (node.getX() == shit.getX() 
						&& node.getY() == shit.getY()) {
					return shit;
				}
			}
		}
		return null;
	}
	
	public void growUp(int length) {
		SnakeNode lastNode = nodes.get(nodes.size()-1);
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
			SnakeNode node = new SnakeNode(x, y, direction);
			nodes.add(node);
		}
	}
	
	public void detectAfterDraw() {
		if (!hitYard() && !hitMe()) {
			Egg egg = eatEgg();
			if (null != egg) {
				yard.getEggs().remove(egg);
				growUp(1);
			}
			Shit shit = eatShit();
			if (null != shit) {
				yard.gameOver(this);
			}
		} else {
			yard.gameOver(this);
		}
	}
	
	public void draw(Graphics g) {
		move();
		
		Color c = g.getColor();

		g.setColor(color);
		
		for (int i=0; i<nodes.size(); i++) {
			int x = nodes.get(i).getX();
			int y = nodes.get(i).getY();
			g.fillRect(x*nodeSize, y*nodeSize, nodeSize, nodeSize);
		}
		g.setColor(c);
		
		detectAfterDraw();
	}
}
