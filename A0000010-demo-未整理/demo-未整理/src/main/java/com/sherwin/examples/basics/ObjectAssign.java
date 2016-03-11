package com.sherwin.examples.basics;

class ObjectAssign {
	
	int x = 1;
	
	public static void main(String args[]) {
		ObjectAssign a = new ObjectAssign();
		
		ObjectAssign b = a;
		
		//改变对象a前，a，b的值是一样的
		System.out.println("a.x is " + a.x);
		System.out.println("b.x is " + b.x);
		
		a.x = 5;
		
		//改变对象a后，a，b的值还是一样的
		System.out.println("a.x is " + a.x);
		System.out.println("b.x is " + b.x);
		
		int x = 3;
		int y = x;
		
		System.out.println("x is " + x);
		System.out.println("y is " + y);
		
		y = 5;
		
		System.out.println("x is " + x);
		System.out.println("y is " + y);
	}
}