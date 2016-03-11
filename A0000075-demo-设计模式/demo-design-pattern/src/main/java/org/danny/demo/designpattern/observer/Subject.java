package org.danny.demo.designpattern.observer;

public interface Subject {

	public void registerObserver(Observer observer);
	
	public void removeObserver(Observer observer);
	
	public void notifyObserver();
}
