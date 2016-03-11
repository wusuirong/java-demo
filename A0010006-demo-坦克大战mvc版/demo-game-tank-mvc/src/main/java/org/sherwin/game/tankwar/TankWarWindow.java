package org.sherwin.game.tankwar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankWarWindow extends Frame {
	private static final int WIDTH;	
	private static final int HEIGHT;
	
	private static final int x, y;
	
	static {
		x = 150;
		y = 30;
		WIDTH = 200;
		HEIGHT = 200;
	}

	private BattleField battleField;
	private Dashboard dashboard;
	
	public TankWarWindow() {
		this.setLayout(new BorderLayout());
		this.setLocation(x, y);
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.BLACK);
		this.setResizable(false);
		
		battleField = new BattleField();
		this.add(battleField, BorderLayout.CENTER);
		
		dashboard = new Dashboard();
		this.add(dashboard, BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter() {

			/* (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.pack();
		this.setVisible(true);
	}
}
