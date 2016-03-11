package org.shewin.game.tetris.view.panel;

import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;

public class MainMenuPanel extends Panel {

	protected Label titleLabel;
	
	protected Button startGameBtn;
	
	protected Button settingBtn;
	
	protected Button aboutBtn;
	
	protected Button exitBtn;
	
	public MainMenuPanel() {
		titleLabel = new Label("Tetris");
		startGameBtn = new Button("Start Game");
		settingBtn = new Button("Settings");
		aboutBtn = new Button("About");
		exitBtn = new Button("Exit");
		
		this.add(titleLabel);
		this.add(startGameBtn);
		this.add(settingBtn);
		this.add(aboutBtn);
		this.add(exitBtn);
	}
}
