package net.other;

import net.other.object.Test;

public class Tester {
	public static void main(String[] args) {
		Test t = new Test(1,2);
		Test.innerClass1 ic = t.new innerClass1();
//		Test.innerClass1 ic = new Test.innerClass1();
		
		t.a = 10;
		ic.a = 100;
		
		System.out.println(ic.getA());
		
//		System.out.println(ic.getOuterA());
	}
}
