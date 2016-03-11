package org.sherwin.designpattern.factory;

public class Client {
	
	public static void main(String[] args) {
		Factory f = null;
		
		f = MainFactory.produce("1");
		Product p = f.produce();
		
	}

}