package org.sherwin.game.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Yard extends Panel {

	public static final int COLS = 25;
	public static final int ROWS = 25;
	public static final int BLOCK_SIZE = 20;
	public static final int WIDTH = BLOCK_SIZE*COLS + 1;
	public static final int HEIGHT = BLOCK_SIZE*ROWS + 1;
	
	private List<Snake> snakes = new ArrayList<Snake>();
	
	private List<Egg> eggs = new ArrayList<Egg>();
	
	private List<Shit> shits = new ArrayList<Shit>();
	
	private Image offScreenImage = null;
	
	private GameWindow gameWindow;
	
	private boolean gameRunning;
	
	private int speed;
	
	public Yard(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.GRAY);

		gameRunning = false;
		speed = 5;
		new Thread(new RepaintThread()).start();
		new Thread(new RandomEventThread()).start();
	}
	
	public void startPlay() {
		gameRunning = true;
	}
	
	public void stopPlay() {
		gameRunning = false;
	}
	
	public void addSnake(Snake snake) {
		snakes.add(snake);
	}
	
	public void addEgg(Egg egg) {
		eggs.add(egg);
	}
	
	public void addShit(Shit shit) {
		shits.add(shit);
	}
	
	public void removeShit() {
		if (0 < shits.size()) {
			shits.remove(0);
		}
	}
	
	public void clearYard() {
		eggs.clear();
		shits.clear();
		snakes.clear();
	}
	
	public void gameOver(Snake snake) {
		if (gameRunning) {
			gameWindow.gameOver();
		}
	}
	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		Color c = g.getColor();
		
		g.setColor(Color.BLACK);
		
		int x=0, y=0;
		for (int i=0; i<COLS; i++) {
			for (int j=0; j<ROWS; j++) {
				x = BLOCK_SIZE*i;
				y = BLOCK_SIZE*j;
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
		
		drawEggs(g);
		drawShits(g);
		drawSnake(g);
		g.setColor(c);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		offScreenImage = this.createImage(WIDTH, HEIGHT);
		paint(offScreenImage.getGraphics());
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private void drawSnake(Graphics g) {
		for (int i=0; i<snakes.size(); i++) {
			snakes.get(i).draw(g);
		}
	}
	
	private void drawEggs(Graphics g) {
		for (int i=0; i<eggs.size(); i++) {
			eggs.get(i).draw(g);
		}
	}
	
	private void drawShits(Graphics g) {
		for (int i=0; i<shits.size(); i++) {
			shits.get(i).draw(g);
		}
	}
	
	private class RepaintThread implements Runnable {

		public void run() {
			while (true) {
				if (gameRunning) {
					Yard.this.repaint();
				}
				try {
					Thread.sleep((11-speed)*100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private class RandomEventThread implements Runnable {

		Random r = new Random(System.currentTimeMillis());
		public void run() {
			while (true) {
				if (gameRunning) {
					int i = r.nextInt(3);
					if (0==i && eggs.size()<3) {
						int col = r.nextInt(Yard.COLS);
						int row = r.nextInt(Yard.ROWS);
						Egg egg = new Egg(col, row, Yard.BLOCK_SIZE);
						Yard.this.addEgg(egg);
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					i = r.nextInt(8);
					if (4<i && shits.size()<3) {
						int col = r.nextInt(Yard.COLS);
						int row = r.nextInt(Yard.ROWS);
						Shit shit = new Shit(col, row, Yard.BLOCK_SIZE);
						Yard.this.addShit(shit);
					} else if (2==i) {
						Yard.this.removeShit();
					}
				}
			}
		}
		
	}

	/**
	 * @return the eggs
	 */
	public List<Egg> getEggs() {
		return eggs;
	}

	/**
	 * @param eggs the eggs to set
	 */
	public void setEggs(List<Egg> eggs) {
		this.eggs = eggs;
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
	 * @return the snakes
	 */
	public List<Snake> getSnakes() {
		return snakes;
	}

	/**
	 * @param snakes the snakes to set
	 */
	public void setSnakes(List<Snake> snakes) {
		this.snakes = snakes;
	}

	/**
	 * @return the shits
	 */
	public List<Shit> getShits() {
		return shits;
	}

	/**
	 * @param shits the shits to set
	 */
	public void setShits(List<Shit> shits) {
		this.shits = shits;
	}

}
