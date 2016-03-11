package org.sherwin.game.snake.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.sherwin.game.snake.controller.GameControllerImpl;
import org.sherwin.game.snake.model.Food;
import org.sherwin.game.snake.model.Ground;
import org.sherwin.game.snake.model.Rock;
import org.sherwin.game.snake.model.Snake.Node;

public class Yard extends Panel implements Observer {

	private GameControllerImpl gameController;

	private Ground ground;

	public static final int BLOCK_SIZE = 20;
	public static int WIDTH;
	public static int HEIGHT;

	private Image offScreenImage = null;

	public Yard(GameControllerImpl gameController, Ground ground) {
		this.gameController = gameController;
		this.ground = ground;

		WIDTH = BLOCK_SIZE * ground.getWidth() + 1;
		HEIGHT = BLOCK_SIZE * ground.getHeight() + 1;
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.green);

	}

	public void update(Observable o, Object arg) {
		this.repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		drawGround(g);
		drawFoods(g);
		drawSnake(g);
		drawRocks(g);		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		offScreenImage = this.createImage(WIDTH, HEIGHT);
		paint(offScreenImage.getGraphics());
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	private void drawGround(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		Color c = g.getColor();

		g.setColor(Color.BLACK);

		int x = 0, y = 0;
		for (int i = 0; i < ground.getWidth(); i++) {
			for (int j = 0; j < ground.getHeight(); j++) {
				x = BLOCK_SIZE * i;
				y = BLOCK_SIZE * j;
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
		g.setColor(c);
	}

	private void drawSnake(Graphics g) {
		Color c = g.getColor();

		g.setColor(Color.red);

		List<Node> nodes = ground.getSnake().getNodes();
		for (int i = 0; i < nodes.size(); i++) {
			int x = nodes.get(i).getX();
			int y = nodes.get(i).getY();
			g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
		}
		g.setColor(c);
	}

	private void drawFoods(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.blue);
		List<Food> foods = ground.getFoods();
		for (int i = 0; i < foods.size(); i++) {
			int x = foods.get(i).getX();
			int y = foods.get(i).getY();
			g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
		}
		g.setColor(c);
	}

	private void drawRocks(Graphics g) {
		Color c = g.getColor();

		g.setColor(Color.gray);

		List<Rock> rocks = ground.getRocks();
		for (int i = 0; i < rocks.size(); i++) {
			int x = rocks.get(i).getX();
			int y = rocks.get(i).getY();
			g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
		}
		g.setColor(c);
	}

}
