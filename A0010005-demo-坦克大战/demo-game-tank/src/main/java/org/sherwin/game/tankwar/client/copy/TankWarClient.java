package org.sherwin.game.tankwar.client.copy;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TankWarClient extends Frame {
	
	public static final int graphicsWidth = 800;
	public static final int graphicsHeight = 600;
	private static final int enemyCount = 50;
	private static final int tankWidth = 30;
	private static final int tankHeight = 30;
	
	private List<Tank> myTanks = new ArrayList<Tank>();
	
	private List<Missile> missiles = new ArrayList<Missile>();
	
	private List<Tank> enemys = new ArrayList<Tank>();
	
	private List<Explode> explodes = new ArrayList<Explode>();
	
	private Random r = new Random(new Date().getTime());
	
	private List<Wall> walls = new ArrayList<Wall>();
	
	Image offScreenImage = null;
	
	private NetClient netClient;
	private static final int SERVER_TCP_PORT = 8888;
	
	public TankWarClient() {		
		this.setLocation(new Point(50, 50));
		this.setSize(new Dimension(graphicsWidth, graphicsHeight));
		this.setResizable(false);
		this.setTitle("坦克大战(awt ver) by sherwin");
		this.setBackground(Color.GRAY);
		
		this.initTank();
		this.initWall();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.addKeyListener(new KeyMonitor());
		
		this.setVisible(true);
		
		new Thread(new PaintThread()).start();
		new Thread(new EnemyIntelligenceThread()).start();
		
		netClient = new NetClient(this);
		netClient.connect("127.0.0.1", SERVER_TCP_PORT);
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, graphicsWidth, graphicsHeight);
		
/*		for (Missile missile : missiles) {
			if (!missile.isAlive()) {
				missiles.remove(missile);
			} else {
				missile.draw(g);
			}			
		}*/
		
		for (int i=0; i<walls.size(); i++) {
			walls.get(i).draw(g);
		}
		
		for (int i=0; i<missiles.size(); i++) {
			Missile missile = missiles.get(i);
			missile.draw(g);
			for (int j=0; j<enemys.size(); j++) {
				Tank enemy = enemys.get(j);
				missile.hitTank(enemy);
			}
			for (int j=0; j<myTanks.size(); j++) {
				missile.hitTank(myTanks.get(j));
			}
			for (int j=0; j<walls.size(); j++) {
				missile.hitWall(walls.get(j));
			}
		}
		
		for (int i=0; i<myTanks.size(); i++) {
			for (Wall wall : walls) {
				if (myTanks.get(i).hitWall(wall)) {
					myTanks.get(i).stay();
				}
			}
			for (Tank enemy : enemys) {
				if (myTanks.get(i).hitTank(enemy)) {
					myTanks.get(i).stay();
				}
			}
			myTanks.get(i).draw(g);
		}
		
		for (Tank enemy : enemys) {
			for (Wall wall : walls) {
				if (enemy.hitWall(wall)) {
					enemy.stay();
				}
			}
			for (Tank enemy2 : enemys) {
				if (!enemy.equals(enemy2) && enemy.hitTank(enemy2)) {
					enemy.stay();
				}
			}
			for (Tank myTank : myTanks) {
				if (enemy.hitTank(myTank)) {
					enemy.stay();
				}
			}
			enemy.draw(g);
		}
		
		for (int i=0; i<explodes.size(); i++) {
			explodes.get(i).draw(g);
		}

		g.drawString("enemys: " + enemys.size(), 10, 50);
		if (0 < myTanks.size()) {
			g.drawString("hp: " + myTanks.get(0).getCurrentHp(), 10, 60);
		}
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
	
	@Deprecated
	public void removeMissile() {
		List<Missile> uselessMissile = new ArrayList<Missile>();
		for (Missile missile : missiles) {
			if (!missile.isAlive()) {
				uselessMissile.add(missile);
			}
		}
		missiles.removeAll(uselessMissile);
	}
	
	
	
	private void initTank() {
		Tank myTank = new Tank(50, 500, tankWidth, tankHeight, 1, true, 10, this);
		myTanks.add(myTank);
		
/*		for (int i=0; i<19; i++) {
			Tank enemy = new Tank(i*50 + 150, 150, tankWidth, tankHeight, 1, false, 2, this);
			enemys.add(enemy);
		}*/
	}
	
	private void initWall() {
		Wall wall = new Wall(100, 200, 600, 50);
		walls.add(wall);
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
	
	private class EnemyIntelligenceThread implements Runnable {

		public void run() {
			Direction[] directions = Direction.values();
			while(true) {
				for (int i=0; i<enemys.size(); i++) {
					Tank enemy = enemys.get(i);
					int d = 0;
					if (0 >= enemy.getDirectionConsistency()){
						d = r.nextInt(directions.length);
						enemy.setDirection(directions[d]);
						d = r.nextInt(5);
						enemy.setDirectionConsistency(d);
					} else {
						enemy.setDirectionConsistency(enemy.getDirectionConsistency()-1);
					}
					
					if (0 >= enemy.getFaceConsistency()) {
						d = r.nextInt(directions.length - 1);
						enemy.setFace(directions[d]);
						d = r.nextInt(3);
						enemy.setFaceConsistency(d);
					} else {
						enemy.setFaceConsistency(enemy.getFaceConsistency()-1);
					}
					
					d = r.nextInt(2);
					if (d!=-1) {
						enemy.fire();
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (0 < myTanks.size()) {
				myTanks.get(0).keyPressed(e);
			}
		}

		/* (non-Javadoc)
		 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			if (0 < myTanks.size()) {
				myTanks.get(0).keyReleased(e);
			}
		}

	}

	/**
	 * @return the missiles
	 */
	public List<Missile> getMissiles() {
		return missiles;
	}

	/**
	 * @param missiles the missiles to set
	 */
	public void setMissiles(List<Missile> missiles) {
		this.missiles = missiles;
	}

	/**
	 * @return the enemys
	 */
	public List<Tank> getEnemys() {
		return enemys;
	}

	/**
	 * @param enemys the enemys to set
	 */
	public void setEnemys(List<Tank> enemys) {
		this.enemys = enemys;
	}

	/**
	 * @return the explodes
	 */
	public List<Explode> getExplodes() {
		return explodes;
	}

	/**
	 * @param explodes the explodes to set
	 */
	public void setExplodes(List<Explode> explodes) {
		this.explodes = explodes;
	}

	/**
	 * @return the myTanks
	 */
	public List<Tank> getMyTanks() {
		return myTanks;
	}

	/**
	 * @param myTanks the myTanks to set
	 */
	public void setMyTanks(List<Tank> myTanks) {
		this.myTanks = myTanks;
	}

	/**
	 * @return the graphicswidth
	 */
	public static int getGraphicswidth() {
		return graphicsWidth;
	}

}
