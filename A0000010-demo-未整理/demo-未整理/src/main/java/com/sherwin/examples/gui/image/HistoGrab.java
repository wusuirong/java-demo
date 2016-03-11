package com.sherwin.examples.gui.image;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;

/**
 * 演示从图像得到数组的ImageConsumer接口的功能
 * @author Administrator
 *
 */
public class HistoGrab extends Applet {
	Dimension d;
	Image img;
	int iw, ih;
	int pixels[];
	int w, h;
	int hist[] = new int[256];
	int max_hist = 0;

	public void init() {
		d = getSize();
		w = d.width;
		h = d.height;

		try {
			img = getImage(getDocumentBase(), "com/sherwin/examples/gui/image/hello.jpg");
			MediaTracker t = new MediaTracker(this);
			t.addImage(img, 0);
			t.waitForID(0);
			iw = img.getWidth(null);
			ih = img.getHeight(null);
			pixels = new int[iw * ih];
			PixelGrabber pg = new PixelGrabber(img, 0, 0, iw, ih, pixels, 0, iw);
			pg.grabPixels();
		} catch (InterruptedException e) {
		}
		;

		for (int i = 0; i < iw * ih; i++) {
			int p = pixels[i];
			int r = 0xff & (p >> 16);
			int g = 0xff & (p >> 8);
			int b = 0xff & (p);
			int y = (int) (.33 * r + .56 * g + .11 * b);
			hist[y]++;
		}
		for (int i = 0; i < 256; i++) {
			if (hist[i] > max_hist)
				max_hist = hist[i];
		}
	}

	public void update() {
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
		int x = (w - 256) / 2;
		int lasty = h - h * hist[0] / max_hist;
		for (int i = 0; i < 256; i++, x++) {
			int y = h - h * hist[i] / max_hist;
			g.setColor(new Color(i, i, i));
			g.fillRect(x, y, 1, h);
			g.setColor(Color.red);
			g.drawLine(x - 1, lasty, x, y);
			lasty = y;
		}
	}
}