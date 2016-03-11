package org.sherwin.designpattern;

public interface StateMachine {
	
	public static final int NO_QUARTER = 0;
	public static final int HAS_QUARTER = 1;
	public static final int SOLD = 2;
	public static final int SOLDOUT = 3;

	public void insertQuarter();
	
	public void pushHandler();
	
	public void ejectQuarter();
	
}
