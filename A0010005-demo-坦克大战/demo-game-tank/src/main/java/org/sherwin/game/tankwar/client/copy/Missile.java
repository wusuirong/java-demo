package org.sherwin.game.tankwar.client.copy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Missile {
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private boolean alive;

	private Direction direction = Direction.STOP;
	
	private boolean friend;
	
	private TankWarClient tankWarClient;
	
	public Missile(int x, int y, Direction direction, boolean friend, TankWarClient tankWarClient) {
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.tankWarClient = tankWarClient;
		this.friend = friend;
		speed = 3;
		width = 5;
		height = 5;
		alive = true;
	}
	
	public void draw(Graphics g) {
		this.move();
		
		Color c = g.getColor();
		
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, height);
		
		g.setColor(c);
	}
	
	private void checkOutbound() {
		if (x < 0 || x > TankWarClient.graphicsWidth
				|| y < 0 || y > TankWarClient.graphicsHeight) {
			alive = false;
		}
	}
	
	public Rectangle getRectangle() {
		Rectangle rectangle = new Rectangle(x, y, width, height);
		return rectangle;
	}
	
	public void hitTank(Tank tank) {
		Rectangle tankRectangle = tank.getRectangle();
		Rectangle missileRectangle = this.getRectangle();
		if (alive && tankRectangle.intersects(missileRectangle)
				&& this.friend!=tank.isFriend()) {
			this.alive = false;
			tank.setCurrentHp(tank.getCurrentHp()-1);
			if (0 >= tank.getCurrentHp()) {
				tank.setAlive(false);
				if (tank.isFriend()) {
					tankWarClient.getMyTanks().remove(tank);
				} else {
					tankWarClient.getEnemys().remove(tank);
				}
				tankWarClient.getExplodes().add(new Explode(tank.getX()+tank.getWidth()/2, tank.getY()+tank.getHeight()/2, tankWarClient));
			}			
		}
		/*int minWidth = width + tank.getWidth();
		int realWidth = Math.abs((x + width) - (tank.getX() + tank.getWidth()));
		int minHeight = height + tank.getHeight();
		int realHeight = Math.abs((y + height) - (tank.getY() + tank.getHeight()));
		if (realWidth<minWidth && realHeight<minHeight) {
			this.alive = false;
			tank.setAlive(false);
			tankWarClient.getMissiles().remove(this);
			tankWarClient.getEnemys().remove(tank);
		}*/
	}
	
	public void hitWall(Wall wall) {
		Rectangle wallRectangle = wall.getRectangle();
		Rectangle missileRectangle = this.getRectangle();
		if (wallRectangle.intersects(missileRectangle)) {
			this.alive = false;
		}
	}
	
	private void move() {
		if (!alive) {
			tankWarClient.getMissiles().remove(this);
			return;
		}
		switch (direction) {
		case UP:
			y -= speed;
			break;
		case DOWN:
			y += speed;
			break;
		case LEFT:
			x -= speed;
			break;
		case RIGHT:
			x += speed;
			break;
		case LEFTUP:
			x -= speed;
			y -= speed;			
			break;
		case LEFTDOWN:
			x -= speed;
			y += speed;
			break;
		case RIGHTUP:
			x += speed;
			y -= speed;
			break;
		case RIGHTDOWN:
			x += speed;
			y += speed;
			break;
		}
		checkOutbound();
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
