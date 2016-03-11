package com.sherwin.examples.basics;

class Scope1 {
	public static void main(String args[]) {
		int x = 10; // main函数里有效
		
		// 这个方括号开始了if的作用域
		if (x == 10) {
			int y = 20; // y只在这个作用域有效

			System.out.println("x and y: " + x + " " + y);
			x = y * 2; //x在子作用域里还是有效的
		}
		// y = 100; // y已经无效了
		
		
		
		//for循环是特例，n在花括号外定义
		for (int n = 10; n > 0; n--) { 
			System.out.println("tick " + n);
		}
		// n = 2; //n已经无效了

		
		//只要有花括号，就产生新的作用域
		{
			int z = 30;
		}
		// z = z - 2; //z无效了

		System.out.println("x is " + x);
	}
}