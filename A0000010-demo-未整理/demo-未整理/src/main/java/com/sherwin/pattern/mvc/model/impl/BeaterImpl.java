package com.sherwin.pattern.mvc.model.impl;

import com.sherwin.pattern.mvc.model.intf.BeatModel;
import com.sherwin.pattern.mvc.model.intf.Beater;
import com.sherwin.pattern.mvc.model.intf.BeaterListener;

public class BeaterImpl implements Beater, Runnable {

	public BeaterImpl(BeaterListener beaterListener) {
		this.beaterListener = beaterListener;
		this.play = false;
		Thread t = new Thread(this);
		t.start();
	}
	
	public int getBPM() {
		return bpm;
	}

	public void setBPM(int bpm) {
		this.bpm = bpm;
	}

	public void on() {
		play = true;
	}

	public void off() {
		play = false;
	}

	public void run() {
		while (true) {
			if (play) {
				System.out.println("bpm:" + bpm);
				beaterListener.beepEvent();
			}
			try {
				Thread.sleep(bpm*100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private int bpm;
	
	private boolean play;
	
	private BeaterListener beaterListener;

}
