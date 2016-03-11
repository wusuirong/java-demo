package org.sherwin.game.tankwar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

public class Dashboard extends Panel {
	
	private static final int WIDTH;	
	private static final int HEIGHT;
	
	static {
		WIDTH = 600;
		HEIGHT = 100;
	}

	public Dashboard() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.GREEN);
	}
}
