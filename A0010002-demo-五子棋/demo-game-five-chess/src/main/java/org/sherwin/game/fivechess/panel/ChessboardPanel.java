package org.sherwin.game.fivechess.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.sherwin.game.fivechess.model.Chessboard;

/**
 * 棋盘
 * @author suirongw
 *
 */
public class ChessboardPanel extends Panel {

	private boolean gameRunning;

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final int X_EXCURSION = 25;
	public static final int Y_EXCURSION = 25;
	public static final int BLOCK_SIZE = 25;
	public static final int X_LINES = 19;
	public static final int Y_LINES = 19;
	public static final int CHESS_RADIUS = 12;
	
	private String bgImagePath = "images/chessboard.jpg";
	private BufferedImage bgImage = null;
	
	private Image offScreenImage = null;
	
	private Chessboard chessboard;
	
	public ChessboardPanel() {
		gameRunning = true;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		try {
			bgImage = ImageIO.read(this.getClass().getClassLoader().getResource(bgImagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.addMouseListener(new PutChessListener());
		new Thread(new RepaintThread()).start();
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		Color c = g.getColor();

		g.drawImage(bgImage, 0, 0, null);
		
		for (int i=0; i<X_LINES; i++) {
			for (int j=0; j<Y_LINES; j++) {
				if (1 == chessboard.getPointStatus(i, j)) {
					g.setColor(Color.BLACK);
					g.fillOval(i*BLOCK_SIZE + X_EXCURSION - CHESS_RADIUS, j*BLOCK_SIZE + Y_EXCURSION - CHESS_RADIUS, CHESS_RADIUS*2, CHESS_RADIUS*2);
				} else if (2 == chessboard.getPointStatus(i, j)) {
					g.setColor(Color.WHITE);
					g.fillOval(i*BLOCK_SIZE + X_EXCURSION - CHESS_RADIUS, j*BLOCK_SIZE + Y_EXCURSION - CHESS_RADIUS, CHESS_RADIUS*2, CHESS_RADIUS*2);
				}
			}
		}

		g.setColor(c);
	}
	
	@Override
	public void update(Graphics g) {
		offScreenImage = this.createImage(WIDTH, HEIGHT);
		paint(offScreenImage.getGraphics());
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private class RepaintThread implements Runnable {

		public void run() {
			while (true) {
				if (gameRunning) {
					ChessboardPanel.this.repaint();
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
	
	static boolean black = true;
	private class PutChessListener extends MouseAdapter {

		/* (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
//			System.out.println("x: " + e.getX() + ", y: " + e.getY());
			
			int x = e.getX();
			int y = e.getY();
			int i = (int)Math.round((x - X_EXCURSION - 0.0)/BLOCK_SIZE);
			int j = (int)Math.round((y - Y_EXCURSION - 0.0)/BLOCK_SIZE);
			if (0<=i && i<X_LINES && 0<=j && j<Y_LINES) {
				if (0 == chessboard.getPointStatus(i, j)) {
					if (black) {
						chessboard.setPointStatus(i, j, 1);
					} else {
						chessboard.setPointStatus(i, j, 2);
					}
					black = !black;
				}
			}			
		}
		
	}
}
