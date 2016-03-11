package com.sherwin.pattern.mvc.model.intf;

public interface Beater {

	public void setBPM(int bpm);
	
	public int getBPM();
	
	public void on();
	
	public void off();
}
