package net.other;

import java.io.Serializable;

class Outer {
	public void someOuterMethod() {
		// Line 3
	}

	public class Inner {
	}

	public static void main(String[] argv) {
		Outer o = new Outer();
		// Line 8
	}
}
