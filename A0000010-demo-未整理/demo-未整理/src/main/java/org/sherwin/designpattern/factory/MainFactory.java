package org.sherwin.designpattern.factory;

public class MainFactory {
	
	public static Factory produce(String type) {
		if (type.equals("1")) {
			return new Factory1();
		} else {
			return new Factory2();
		}
	}

}
