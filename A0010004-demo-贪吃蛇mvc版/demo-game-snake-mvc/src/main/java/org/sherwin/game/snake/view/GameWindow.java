package org.sherwin.game.snake.view;

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
import java.util.Observable;
import java.util.Observer;

import org.sherwin.game.snake.controller.GameControllerImpl;
import org.sherwin.game.snake.model.GameStatus;
import org.sherwin.game.snake.model.Ground;
import org.sherwin.game.snake.model.ModelManager;

public class GameWindow extends Frame implements Observer {
	
	private Panel infoPanel;
	private Yard yard;

	private GameControllerImpl gameController;
	private ModelManager modelManager;
	
	private GameOverDialog gameOverDialog;

	public GameWindow(GameControllerImpl gameController, ModelManager modelManager) {
		this.gameController = gameController;
		this.modelManager = modelManager;

		this.setLayout(new BorderLayout());
		
		yard = new Yard(gameController, modelManager.getGround());
		this.add(yard, BorderLayout.CENTER);
		
		infoPanel = new Panel() {

			/* (non-Javadoc)
			 * @see java.awt.Container#paint(java.awt.Graphics)
			 */
			@Override
			public void paint(Graphics g) {
				Color c = g.getColor();
				g.setColor(Color.BLACK);
				g.drawString("speed: " + GameWindow.this.modelManager.getGameSpeed(), 10, 10);
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
		
		this.addKeyListener(new DirectionListener());

		this.setTitle("贪吃蛇 by sherwin wu");
		this.pack();
		this.setLocation();
		this.setVisible(true);
	}
	
	public void endGame() {
		gameOverDialog = new GameOverDialog(this, "游戏结束", true);
	}
	
	public void resetUI() {
		yard.repaint();
	}
	
	public void setLocation() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - yard.WIDTH)/2;
		int y = (d.height - yard.HEIGHT)/2;
		this.setLocation(x, y);
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
			case KeyEvent.VK_W:
				gameController.keyUp();
				break;
			case KeyEvent.VK_S:
				gameController.keyDown();
				break;
			case KeyEvent.VK_A:
				gameController.keyLeft();
				break;
			case KeyEvent.VK_D:
				gameController.keyRight();
				break;
			case KeyEvent.VK_R:
				gameController.resetGame();
				break;
			case KeyEvent.VK_SPACE:
				if (GameStatus.RUNNING.equals(modelManager.getGameStatus())) {
					gameController.pauseGame();
				} else {
					gameController.startGame();
				}				
				break;
			}
			infoPanel.repaint();
		}
	}

	public void update(Observable o, Object arg) {
		yard.repaint();
		if (GameStatus.END.equals(modelManager.getGameStatus())) {
			endGame();
		}
	}
}
