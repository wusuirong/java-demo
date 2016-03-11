package com.sherwin.examples.basics;

class Scope2 {
	public static void main(String args[]) {
		int x = 10;
		
		int i = func1(x); //把x的值复制给func1的形参a，注意不是直接把x传入func1，只是复制，返回值也通过复制方式传给i
		
		//System.out.println("r is " + r); //func1中的变量r是看不到的		
		System.out.println("i is " + i);
		System.out.println("x is " + x);
	}
	
	public static int func1(int a) {//形参a也只在函数func1里有效，相当于func1的临时变量
		int s = 10; //s只在func1内有效
		int r = a + 2;
		return r; //r是存放返回值的临时变量，在func1外是无效的，但它的值可以被复制出去，给main的i
	}
}