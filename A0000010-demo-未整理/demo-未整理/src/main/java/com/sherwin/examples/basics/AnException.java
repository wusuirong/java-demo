package com.sherwin.examples.basics;

public class AnException {

	public static void main(String[] args) {
		//严加看管可能发生异常的代码
		try {
			AnException.dosth();
		} catch (ArithmeticException e) {//出异常时的处理
			System.out.println("异常已经被捕获，请放心");
		}
		
		//处理完异常后继续运行
		System.out.println("谢谢你的使用");
	}
	
	//函数声明自己可能会发生异常
	public static void dosth() throws ArithmeticException {
		int a = 5, b = 0;
		int c = a/b;
		System.out.println(c);
	}
}
