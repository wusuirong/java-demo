package com.sherwin.examples.applet;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/*
 * 球拍
 */
public class Racket implements MouseMotionListener {
	
	public int x = 10;
	public int y = 10;
	public int width = 30;
	public int height = 10;
	public Color color;
	BallGame3 ballGame;
	
	public Racket(BallGame3 ballGame, int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.ballGame = ballGame;
	}

	public void mouseMoved(MouseEvent e) {
		if (e.getX()+width < ballGame.getWidth()) {
			this.x = e.getX();
		}
		
		ballGame.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}