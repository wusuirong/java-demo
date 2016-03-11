package com.sherwin.pattern.mvc.model.intf;

public interface BeatModel {
	
	public void start();
	
	public void stop();
	
	public void setBPM(int bpm);
	
	public int getBPM();
	
	public void registerObserver(BeatObserver o);
	
	public void removeObserver(BeatObserver o);
	
	public void registerObserver(BPMObserver o);
	
	public void removeObserver(BPMObserver o);

}
