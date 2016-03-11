package com.sherwin.examples.basics;

public class This {
	
	String name; //表示我的名字的属性
	
	/*
	 * 构造函数，名字和类名一样
	 * 有一个形参name
	 * 形参name默认覆盖属性name，要使用属性name，就通过this引用
	 */
	This(String name) {
		this.name = name;
	}
	
	This returnMe() {
		return this; //把指向自己的引用返回
	}
	
	void sayHi() {
		System.out.println("My name is " + name); //这个方法不存在叫name的临时变量，所以这里的name是属性
	}

	public static void main(String[] args) {
		This me = new This("wusuirong");
		
		This myCopy = me.returnMe();
		
		myCopy.sayHi();
	}

}
