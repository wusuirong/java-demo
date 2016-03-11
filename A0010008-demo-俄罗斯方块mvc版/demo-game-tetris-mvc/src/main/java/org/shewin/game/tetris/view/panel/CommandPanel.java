package org.shewin.game.tetris.view.panel;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;

public class CommandPanel extends Panel {
	public static final int COLS = 4;
	public static final int ROWS = 2;
	public static final int BLOCK_SIZE = 20;
	public static final int WIDTH = BLOCK_SIZE*COLS;
	public static final int HEIGHT = BLOCK_SIZE*ROWS + 1;
	
	private Button startButton;
	private Button pauseButton;
	private Button resetButton;
	
	public CommandPanel() {
		GridLayout gridLayout = new GridLayout(3,1);
		gridLayout.setHgap(5);
		gridLayout.setVgap(5);
		this.setLayout(gridLayout);
		
		startButton = new Button("Start");
		this.add(startButton);
		
		pauseButton = new Button("Pause");
		this.add(pauseButton);
		
		resetButton = new Button("Reset");
		this.add(resetButton);
	}
	
}
