package org.sherwin.game.tankwar.client.copy;

import java.awt.Color;
import java.awt.Graphics;

public class Explode {
	
	int x,y;
	boolean alive = true;
	int[] diameters = {5, 10, 15, 20, 25, 30, 35, 30, 25, 20, 15, 10, 5};
	int currentDiameter = 0;
	
	TankWarClient tankWarClient;
	
	public Explode(int x, int y, TankWarClient tankWarClient) {
		this.x = x;
		this.y = y;
		this.tankWarClient = tankWarClient;
	}
	
	public void draw(Graphics g) {
		if (currentDiameter < diameters.length) {
			Color c = g.getColor();
		
			g.setColor(Color.YELLOW);
		
			int diameter = diameters[currentDiameter];
			g.fillOval(x-diameter/2, y-diameter/2, diameter, diameter);
			
			g.setColor(c);
			
			currentDiameter++;
		} else {
			alive = false;
			tankWarClient.getExplodes().remove(this);
		}
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
