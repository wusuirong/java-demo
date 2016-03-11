package org.sherwin.game.snake;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class GameWindow extends Frame {

	private Yard yard;
	
	private Panel infoPanel;
	
	private GameOverDialog gameOverDialog;
	
	private Random r = new Random(System.currentTimeMillis());
	
	public GameWindow() {
		this.setLayout(new BorderLayout());
		yard = new Yard(this);
		this.add(yard, BorderLayout.CENTER);
		infoPanel = new Panel() {

			/* (non-Javadoc)
			 * @see java.awt.Container#paint(java.awt.Graphics)
			 */
			@Override
			public void paint(Graphics g) {
				Color c = g.getColor();
				g.setColor(Color.BLACK);
				g.drawString("speed: " + yard.getSpeed(), 10, 10);
				g.setColor(c);
			}
			
		};
		infoPanel.setPreferredSize(new Dimension(yard.WIDTH, 30));
		this.add(infoPanel, BorderLayout.SOUTH);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setTitle("贪吃蛇 by sherwin wu");
		this.pack();
		this.setLocation();
		this.setVisible(true);
		
		this.startGame();
	}
	
	public void startGame() {
		int x = r.nextInt(yard.WIDTH);
		int y = r.nextInt(yard.HEIGHT);
		int length = r.nextInt(2)+1;
		Snake snake = new Snake(5, 5, length, yard.BLOCK_SIZE, Direction.RIGHT, Color.RED, yard);
		yard.addSnake(snake);
		
		x = r.nextInt(yard.WIDTH);
		y = r.nextInt(yard.HEIGHT);
		length = r.nextInt(2)+1;
		snake = new Snake(15, 15, length, yard.BLOCK_SIZE, Direction.RIGHT, Color.YELLOW, yard);
		yard.addSnake(snake);
		
		yard.startPlay();
		this.addKeyListener(new DirectionListener());
	}
	
	public void resetGame() {
		yard.stopPlay();
		yard.clearYard();
		startGame();
	}
	
	public void setLocation() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - yard.WIDTH)/2;
		int y = (d.height - yard.HEIGHT)/2;
		this.setLocation(x, y);
	}
	
	public void gameOver() {
		yard.stopPlay();
		gameOverDialog = new GameOverDialog(this, "游戏结束", true);
	}
	
	private class GameOverDialog extends Dialog {

		public GameOverDialog(Frame owner, String title, boolean modal) {
			super(owner, title, modal);
			Button b = new Button("ok");
			b.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			this.add(b);
			
			this.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
				
			});

			this.pack();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (d.width - this.getWidth())/2;
			int y = (d.height - this.getHeight())/2;
			this.setLocation(x, y);
			this.setVisible(true);
		}
	}
	
	private class DirectionListener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			// the first snake
			case KeyEvent.VK_W:
				yard.getSnakes().get(0).changeDirection(Direction.UP);
				break;
			case KeyEvent.VK_S:
				yard.getSnakes().get(0).changeDirection(Direction.DOWN);
				break;
			case KeyEvent.VK_A:
				yard.getSnakes().get(0).changeDirection(Direction.LEFT);
				break;
			case KeyEvent.VK_D:
				yard.getSnakes().get(0).changeDirection(Direction.RIGHT);
				break;
			// the second snake
			case KeyEvent.VK_UP:
				yard.getSnakes().get(1).changeDirection(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				yard.getSnakes().get(1).changeDirection(Direction.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				yard.getSnakes().get(1).changeDirection(Direction.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				yard.getSnakes().get(1).changeDirection(Direction.RIGHT);
				break;
			case KeyEvent.VK_R:
				GameWindow.this.resetGame();
				break;
			case KeyEvent.VK_EQUALS:
				if (10>yard.getSpeed()) {
					yard.setSpeed(yard.getSpeed()+1);
				}
				break;
			case KeyEvent.VK_MINUS:				
				if (1<yard.getSpeed()) {
					yard.setSpeed(yard.getSpeed()-1);
				}
				break;
			}
			infoPanel.repaint();
		}
	}
}
