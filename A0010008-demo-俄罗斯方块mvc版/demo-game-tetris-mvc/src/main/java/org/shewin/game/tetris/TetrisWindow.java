package org.shewin.game.tetris;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.shewin.game.tetris.view.panel.AboutPanel;
import org.shewin.game.tetris.view.panel.GamePanel;
import org.shewin.game.tetris.view.panel.MainMenuPanel;
import org.shewin.game.tetris.view.panel.SettingPanel;

public class TetrisWindow extends Frame {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	protected Panel container;
	protected MainMenuPanel mainMenuPanel;
	protected AboutPanel aboutPanel;
	protected SettingPanel settingPanel;
	protected GamePanel gamePanel;
	
	protected CardLayout cardLayout;
	
	public TetrisWindow() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setBackground(Color.gray);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);
		this.setTitle("Tetris by sherwin wu");
		
		initComponents();
		
		this.pack();
		this.setLocation();
		this.setVisible(true);
	}
	
	public void initComponents() {
		mainMenuPanel = new MainMenuPanel();
		aboutPanel = new AboutPanel();
		settingPanel = new SettingPanel();
		gamePanel = new GamePanel();
		
		container = new Panel();
		cardLayout = new CardLayout();
		container.setLayout(cardLayout);
		
		container.add(mainMenuPanel,"mainMenu");
		container.add(aboutPanel,"about");
		container.add(settingPanel,"setting");
		container.add(gamePanel,"game");
		
		this.setLayout(new BorderLayout());
		this.add(container);
		
		cardLayout.show(container, "mainMenu");
	}
	
	public void setLocation() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - WIDTH)/2;
		int y = (d.height - HEIGHT)/2;
		this.setLocation(x, y);
	}
}
