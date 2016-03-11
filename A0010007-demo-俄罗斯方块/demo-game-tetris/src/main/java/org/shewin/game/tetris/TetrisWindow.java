package org.shewin.game.tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.shewin.game.tetris.panel.CommandPanel;
import org.shewin.game.tetris.panel.ConstructionPanel;
import org.shewin.game.tetris.panel.PreviewPanel;
import org.shewin.game.tetris.panel.ScorePanel;

public class TetrisWindow extends Frame {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private ConstructionPanel constructionPanel;
	private PreviewPanel previewPanel;
	private ScorePanel scorePanel;
	private CommandPanel commandPanel;

	public TetrisWindow() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setBackground(Color.gray);
//		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);
		this.setTitle("俄罗斯方块 by sherwin wu");
		
		initComponents();
		
		this.pack();
		this.setLocation();
		this.setVisible(true);
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		
		constructionPanel = new ConstructionPanel();		
		this.add(constructionPanel, BorderLayout.CENTER);
		
		Panel panel = new Panel();		
		this.add(panel, BorderLayout.EAST);
		
		GridLayout gridLayout = new GridLayout(3,1);
		gridLayout.setHgap(5);
		gridLayout.setVgap(5);
		panel.setLayout(gridLayout);
		previewPanel = new PreviewPanel();
		panel.add(previewPanel);
		
		scorePanel = new ScorePanel();
		panel.add(scorePanel);
		
		commandPanel = new CommandPanel();
		panel.add(commandPanel);
	}
	
	public void setLocation() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - WIDTH)/2;
		int y = (d.height - HEIGHT)/2;
		this.setLocation(x, y);
	}
}
