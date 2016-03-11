package org.sherwin.game.tankwar.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Tank {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	
	private int currentHp;
	private int maxHp;
	
	private int oldX;
	private int oldY;
	
	private TankWarClient tankWarClient;
	
	private boolean bU;
	private boolean bD;
	private boolean bL;
	private boolean bR;
	
	private Direction direction = Direction.STOP;
	private Direction face = Direction.RIGHT;
	
	private boolean friend;
	
	private boolean alive;
	
	private int directionConsistency = 0;
	private int faceConsistency = 0;
	
	private int id;
	
	public Tank(int x, int y, int width, int height, int speed, boolean friend, int hp, TankWarClient tankWarClient) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.tankWarClient = tankWarClient;
		this.friend = friend;
		this.alive = true;
		this.maxHp = hp;
		this.currentHp = maxHp;
	}
	
	public void draw(Graphics g) {
		if (this.friend) {
			this.locateDirection();
		}		
		this.move();
		Color c = g.getColor();
		
		if (friend) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.GREEN);
		}
		g.fillOval(x, y, width, height);
		
		g.setColor(Color.ORANGE);
		g.fillRect(x, y-10, width, 10);
		g.setColor(Color.RED);
		g.fillRect(x, y-10, (int)(currentHp*1.0/maxHp*width), 10);
		
		g.drawString("id:" + id, x, y-20);
		
		g.setColor(c);

		this.drawBarrel(g);
	}
	
	public void drawBarrel(Graphics g) {
		int x1=0, y1=0, x2=0, y2=0;
		x1 = x + width/2;
		y1 = y + height/2;
		switch (face) {
		case UP:
			x2 = x1;
			y2 = y;
			break;
		case DOWN:
			x2 = x1;
			y2 = y + height;
			break;
		case LEFT:
			x2 = x;
			y2 = y1;
			break;
		case RIGHT:
			x2 = x + width;
			y2 = y1;
			break;
		case LEFTUP:
			x2 = x;
			y2 = y;
			break;
		case LEFTDOWN:
			x2 = x;
			y2 = y + height;
			break;
		case RIGHTUP:
			x2 = x + width;
			y2 = y;
			break;
		case RIGHTDOWN:
			x2 = x + width;
			y2 = y + height;
			break;
		}
		
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(c);
	}
	
	private void move() {
		oldX = x;
		oldY = y;
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
		case STOP:
			break;
		}
		if (x < 0) {
			x = 0;
		}
		if (y < 30) {
			y = 30;
		}
		if (x > TankWarClient.graphicsWidth - width) {
			x = TankWarClient.graphicsWidth - width;
		}
		if (y > TankWarClient.graphicsHeight - height) {
			y = TankWarClient.graphicsHeight - height;
		}
	}
	
	private void locateDirection() {
		if (bU && !bD && !bL && !bR) {
			direction = Direction.UP;
		} else if (!bU && bD && !bL && !bR) {
			direction = Direction.DOWN;
		} else if (!bU && !bD && bL && !bR) {
			direction = Direction.LEFT;
		} else if (!bU && !bD && !bL && bR) {
			direction = Direction.RIGHT;
		} else if (bU && !bD && bL && !bR) {
			direction = Direction.LEFTUP;
		} else if (!bU && bD && bL && !bR) {
			direction = Direction.LEFTDOWN;
		} else if (bU && !bD && !bL && bR) {
			direction = Direction.RIGHTUP;
		} else if (!bU && bD && !bL && bR) {
			direction = Direction.RIGHTDOWN;
		} else if (!bU && !bD && !bL && !bR) {
			direction = Direction.STOP;
		}
		if (!direction.equals(Direction.STOP)) {
			face = direction;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			bU = true;
			break;
		case KeyEvent.VK_S:
			bD = true;
			break;
		case KeyEvent.VK_A:
			bL = true;
			break;
		case KeyEvent.VK_D:
			bR = true;
			break;
		case KeyEvent.VK_UP:
			speed = (10 > speed)?speed+1:speed;
			break;
		case KeyEvent.VK_DOWN:
			speed = (1 < speed)?speed-1:speed;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			bU = false;
			break;
		case KeyEvent.VK_S:
			bD = false;
			break;
		case KeyEvent.VK_A:
			bL = false;
			break;
		case KeyEvent.VK_D:
			bR = false;
			break;
		case KeyEvent.VK_J:
			fire();
			break;
		}
	}
	
	public boolean hitWall(Wall wall) {
		Rectangle wallRectangle = wall.getRectangle();
		Rectangle tankRectangle = this.getRectangle();
		if (wallRectangle.intersects(tankRectangle)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hitTank(Tank tank) {
		Rectangle tankRectangle = tank.getRectangle();
		Rectangle myRectangle = this.getRectangle();
		if (myRectangle.intersects(tankRectangle)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void stay() {
		x = oldX;
		y = oldY;
	}

	public void fire() {
		Missile missile = new Missile(x + width/2, y + height/2, face, this.friend, tankWarClient);
		tankWarClient.getMissiles().add(missile);
	}
	
	public Rectangle getRectangle() {
		Rectangle rectangle = new Rectangle(x, y, width, height);
		return rectangle;
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
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
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

	/**
	 * @return the face
	 */
	public Direction getFace() {
		return face;
	}

	/**
	 * @param face the face to set
	 */
	public void setFace(Direction face) {
		this.face = face;
	}

	/**
	 * @return the directionConsistency
	 */
	public int getDirectionConsistency() {
		return directionConsistency;
	}

	/**
	 * @param directionConsistency the directionConsistency to set
	 */
	public void setDirectionConsistency(int directionConsistency) {
		this.directionConsistency = directionConsistency;
	}

	/**
	 * @return the faceConsistency
	 */
	public int getFaceConsistency() {
		return faceConsistency;
	}

	/**
	 * @param faceConsistency the faceConsistency to set
	 */
	public void setFaceConsistency(int faceConsistency) {
		this.faceConsistency = faceConsistency;
	}

	/**
	 * @return the tankWarClient
	 */
	public TankWarClient getTankWarClient() {
		return tankWarClient;
	}

	/**
	 * @param tankWarClient the tankWarClient to set
	 */
	public void setTankWarClient(TankWarClient tankWarClient) {
		this.tankWarClient = tankWarClient;
	}

	/**
	 * @return the friend
	 */
	public boolean isFriend() {
		return friend;
	}

	/**
	 * @param friend the friend to set
	 */
	public void setFriend(boolean friend) {
		this.friend = friend;
	}

	/**
	 * @return the currentHp
	 */
	public int getCurrentHp() {
		return currentHp;
	}

	/**
	 * @param currentHp the currentHp to set
	 */
	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	/**
	 * @return the maxHp
	 */
	public int getMaxHp() {
		return maxHp;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


}
