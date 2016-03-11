package org.shewin.game.tetris.view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

import org.shewin.game.tetris.model.shape.Shape;

public class ConstructionPanel extends Panel {
	public static final int COLS = 10;
	public static final int ROWS = 20;
	public static final int BLOCK_SIZE = 20;
	public static final int WIDTH = BLOCK_SIZE*COLS + 1;
	public static final int HEIGHT = BLOCK_SIZE*ROWS + 1;
	
	private Shape shape;
	
	private int[][] blocks = new int[COLS][ROWS];
	
	public ConstructionPanel() {

	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		Color c = g.getColor();
		
		g.setColor(Color.GREEN);
		
		//画棋盘
		int x=0, y=0;
		for (int i=0; i<COLS; i++) {
			for (int j=0; j<ROWS; j++) {
				x = BLOCK_SIZE*i;
				y = BLOCK_SIZE*j;
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
			}
		}


		g.setColor(c);
	}
	
	private Image offScreenImage = null;
	
	@Override
	public void update(Graphics g) {
		offScreenImage = this.createImage(WIDTH, HEIGHT);
		paint(offScreenImage.getGraphics());
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
}
