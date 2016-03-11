package org.sherwin.game.fivechess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.sherwin.game.fivechess.panel.ChessboardPanel;
import org.sherwin.game.fivechess.panel.CommandPanel;
import org.sherwin.game.fivechess.panel.InfoPanel;

public class FiveChessWindow extends Frame {
	
	private ChessboardPanel chessboardPanel;
	private CommandPanel commandPanel;
	private InfoPanel infoPanel;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public FiveChessWindow() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.gray);
		this.setResizable(false);
		this.setTitle("五子棋 by sherwin wu");
		
		initComponents();
		
		this.pack();
		this.setLocation();
		this.setVisible(true);
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());		
		chessboardPanel = new ChessboardPanel();		
		this.add(chessboardPanel, BorderLayout.CENTER);
		
		Panel panel = new Panel();
		this.add(panel, BorderLayout.EAST);
		
		panel.setLayout(new GridLayout(5,1));
		infoPanel = new InfoPanel();
		panel.add(infoPanel);
		for (int i=0; i<3; i++) {
			panel.add(new Panel());
		}
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
