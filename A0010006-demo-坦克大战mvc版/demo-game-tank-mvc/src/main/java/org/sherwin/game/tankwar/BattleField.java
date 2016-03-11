package org.sherwin.game.tankwar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class BattleField extends Panel {
	
	private static final int COLS = 15;
	private static final int ROWS = 15;
	private static final int BLOCK_SIZE = 40;
	private static final int WIDTH = COLS * BLOCK_SIZE + 1;
	private static final int HEIGHT = ROWS * BLOCK_SIZE + 1;
	
	private Image tank;

	public BattleField() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.BLACK);
		
		tank = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource("images/tank_right.PNG"));
		Toolkit.getDefaultToolkit().prepareImage(tank, -1, -1, null);
	}

	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		int x=0,y=0;
		for (int i=0; i<COLS; i++) {
			for (int j=0; j<ROWS; j++) {
				x = i*BLOCK_SIZE;
				y = j*BLOCK_SIZE;
				
				Color c = g.getColor();
				g.setColor(Color.green);
				g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
				g.setColor(Color.RED);
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
				g.drawImage(tank, x, y, null);
				g.setColor(c);
			}
		}
	}

}
