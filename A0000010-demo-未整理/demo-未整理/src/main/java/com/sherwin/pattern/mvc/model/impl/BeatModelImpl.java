package com.sherwin.pattern.mvc.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sherwin.pattern.mvc.model.intf.BPMObserver;
import com.sherwin.pattern.mvc.model.intf.BeatModel;
import com.sherwin.pattern.mvc.model.intf.BeatObserver;
import com.sherwin.pattern.mvc.model.intf.Beater;
import com.sherwin.pattern.mvc.model.intf.BeaterListener;

public class BeatModelImpl implements BeatModel, BeaterListener {
	
	public BeatModelImpl() {
		bpm = 0;
		beatObservers = new ArrayList();
		BPMObservers = new ArrayList();
		beater = new BeaterImpl(this);
	}

	public void setBPM(int bpm) {
		this.bpm = bpm;
		beater.setBPM(bpm);
		for (Iterator it = BPMObservers.iterator(); it.hasNext();) {
			BPMObserver BPMObserver = (BPMObserver)it.next();
			BPMObserver.bpmChanged();
		}
	}
	
	public int getBPM() {
		return bpm;
	}
	
	public void start() {
		beater.on();
	}

	public void stop() {
		beater.off();
	}

	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}

	public void registerObserver(BPMObserver o) {
		BPMObservers.add(o);
	}

	public void removeObserver(BeatObserver o) {
		beatObservers.remove(o);
	}

	public void removeObserver(BPMObserver o) {
		BPMObservers.remove(o);
	}
	

	public void beepEvent() {
		for (Iterator it = beatObservers.iterator(); it.hasNext();) {
			BeatObserver beatObserver = (BeatObserver)it.next();
			beatObserver.beated();
		}
	}

	private int bpm;
	
	private Beater beater;
	
	private List beatObservers;
	
	private List BPMObservers;

}
