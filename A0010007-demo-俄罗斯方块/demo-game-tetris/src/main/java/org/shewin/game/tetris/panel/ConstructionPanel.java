package org.shewin.game.tetris.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import org.shewin.game.tetris.shape.Shape;

public class ConstructionPanel extends Panel {
	public static final int COLS = 12;
	public static final int ROWS = 20;
	public static final int BLOCK_SIZE = 20;
	public static final int WIDTH = BLOCK_SIZE*COLS + 1;
	public static final int HEIGHT = BLOCK_SIZE*ROWS + 1;
	
	private List<Shape> shapes = new ArrayList<Shape>();
	
	public ConstructionPanel() {
		gameRunning = true;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		new Thread(new RepaintThread()).start();
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		Color c = g.getColor();
		
		g.setColor(Color.GREEN);
		
		int x=0, y=0;
		for (int i=0; i<COLS; i++) {
			for (int j=0; j<ROWS; j++) {
				x = BLOCK_SIZE*i;
				y = BLOCK_SIZE*j;
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
		
		for (Shape shape : shapes) {
			shape.draw(g);
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
	
	private boolean gameRunning;
	
	private class RepaintThread implements Runnable {

		public void run() {
			while (true) {
				if (gameRunning) {
					ConstructionPanel.this.repaint();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
