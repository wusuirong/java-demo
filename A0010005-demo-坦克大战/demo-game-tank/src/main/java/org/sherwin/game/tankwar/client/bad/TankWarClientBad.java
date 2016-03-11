package org.sherwin.game.tankwar.client.bad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankWarClientBad extends Frame {
	
	private static final int graphicsWidth = 800;
	private static final int graphicsHeight = 600;
	private static final int enemyCount = 50;
	private static final int tankWidth = 30;
	private static final int tankHeight = 30;
	
	Tank myTank;
	Image offScreenImage = null;
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, graphicsWidth, graphicsHeight);
		myTank.draw(g);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		if (null == offScreenImage) {
			offScreenImage = this.createImage(graphicsWidth, graphicsHeight);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public TankWarClientBad() {		
		this.setLocation(new Point(50, 50));
		this.setSize(new Dimension(graphicsWidth, graphicsHeight));
		this.setResizable(false);
		this.setTitle("坦克大战(awt ver) by sherwin");
		this.setBackground(Color.GRAY);
		
		this.initTank();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.addKeyListener(new KeyMonitor());
		
		this.setVisible(true);
		
		new Thread(new PaintThread()).start();
	}
	
	private void initTank() {
		myTank = new Tank();
		myTank.setX(50);
		myTank.setY(50);
		myTank.setWidth(tankWidth);
		myTank.setHeight(tankHeight);
		myTank.setSpeed(1);
	}
	
	private class PaintThread implements Runnable {
		public void run() {
			while (true){
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
	}
	
	public static void main(String[] args) {
		TankWarClientBad tankWar = new TankWarClientBad();
	}
}
