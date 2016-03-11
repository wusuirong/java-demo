package com.sherwin.examples.basics;

class Scope3 {
	
	int x = 1;
	
	public void func1(int i) { //i是形参，离开func1后就无效
		
		int a = 10; //a是临时变量，离开func1后就无效
		
		x = i + 1; //x是对象的属性，不会因为离开func1而无效
	}
	
	public static void main(String args[]) {
		Scope3 s = new Scope3();
		
		s.func1(5);
		
		System.out.println(s.x);
	}
}