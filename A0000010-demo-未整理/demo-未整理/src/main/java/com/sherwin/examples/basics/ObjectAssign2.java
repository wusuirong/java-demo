package com.sherwin.examples.basics;

class ObjectAssign2 {
	
	int x = 1;
	
	public static void main(String args[]) {
		ObjectAssign2 a = new ObjectAssign2();
		int i = 10;
		
		System.out.println(a.x);
		changeObject(a);
		System.out.println(a.x);
		
		System.out.println(i);
		changeInt(i);
		System.out.println(i);
	}
	
	public static void changeObject(ObjectAssign2 obj) {
		obj.x = obj.x + 1;
	}
	
	public static void changeInt(int i) {
		i = i + 1;
	}
}