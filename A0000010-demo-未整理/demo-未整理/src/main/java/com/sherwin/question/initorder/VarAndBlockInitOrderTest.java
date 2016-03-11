package com.sherwin.question.initorder;

/**
 * 变量和初始化块间执行顺序和他们在类中的定义顺序有关
 * 
 */
public class VarAndBlockInitOrderTest {
	// 静态变量
	public static TestA a1 = new TestA("静态变量");

	// 静态初始化块
	static {
		System.out.println("静态初始化块");
	}

	// 静态变量
	public static TestB b1 = new TestB("静态变量");

	// 变量
	public TestA a2 = new TestA("变量");

	// 初始化块
	{
		System.out.println("初始化块");
	}

	// 变量
	public TestB b2 = new TestB("变量");

	// 静态变量
	public static TestB b3 = new TestB("静态变量");

	public static void main(String[] args) {
		new VarAndBlockInitOrderTest();
	}
}

class TestA {
	public TestA(String str) {
		System.out.println("Test--A " + str);
	}
}

class TestB {
	public TestB(String str) {
		System.out.println("Test--B " + str);
	}
}
