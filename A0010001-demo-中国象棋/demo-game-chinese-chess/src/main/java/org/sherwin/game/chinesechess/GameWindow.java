package org.sherwin.game.chinesechess;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameWindow extends Frame {

	private BufferedImage bgImage = null;
	
	public GameWindow() {
		this.setPreferredSize(new Dimension(800,600));
		this.setSize(new Dimension(800,600));
		this.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		URL imgUrl = this.getClass().getClassLoader().getResource("images/chessboard.jpg");
		try {
			bgImage = ImageIO.read(imgUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(bgImage, 0, 0, this);
	}
	
	
}
