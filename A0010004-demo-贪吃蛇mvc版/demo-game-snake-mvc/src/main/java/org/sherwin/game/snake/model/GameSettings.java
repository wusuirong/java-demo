package org.sherwin.game.snake.model;

public class GameSettings {

	private int speed;
	private int groundWidth, groundHeight;
	
	public GameSettings(int speed, int groundWidth, int groundHeight) {
		this.speed = speed;
		this.groundHeight = groundHeight;
		this.groundWidth = groundWidth;
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
	 * @return the groundWidth
	 */
	public int getGroundWidth() {
		return groundWidth;
	}

	/**
	 * @param groundWidth the groundWidth to set
	 */
	public void setGroundWidth(int groundWidth) {
		this.groundWidth = groundWidth;
	}

	/**
	 * @return the groundHeight
	 */
	public int getGroundHeight() {
		return groundHeight;
	}

	/**
	 * @param groundHeight the groundHeight to set
	 */
	public void setGroundHeight(int groundHeight) {
		this.groundHeight = groundHeight;
	}
}
