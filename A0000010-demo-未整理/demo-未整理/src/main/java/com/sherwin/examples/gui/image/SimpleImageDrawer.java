package com.sherwin.examples.gui.image;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 加载图像的例子
 * @author Administrator
 *
 */
public class SimpleImageDrawer extends Applet {

	Image img;
	
	public void init() {
		img = getImage(getDocumentBase(), "com/sherwin/examples/gui/image/hello.jpg");
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
}
