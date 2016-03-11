package org.shewin.game.tetris.view.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class PreviewPanel extends Panel {
	public static final int COLS = 4;
	public static final int ROWS = 5;
	public static final int BLOCK_SIZE = 20;
	public static final int WIDTH = BLOCK_SIZE*COLS + 1;
	public static final int HEIGHT = BLOCK_SIZE*ROWS + 1;
	
	public PreviewPanel() {
		gameRunning = true;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		new Thread(new RepaintThread()).start();
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		Color c = g.getColor();
		
		g.setColor(Color.GREEN);
		
		g.drawRect(0, 0, WIDTH, HEIGHT);
		
		int x=0, y=0;
		for (int i=0; i<COLS; i++) {
			for (int j=1; j<ROWS; j++) {
				x = BLOCK_SIZE*i;
				y = BLOCK_SIZE*j;
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
		g.drawString("Next Block", 3, 15);

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
					PreviewPanel.this.repaint();
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
