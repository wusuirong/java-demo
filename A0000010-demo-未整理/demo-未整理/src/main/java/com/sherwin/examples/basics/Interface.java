package com.sherwin.examples.basics;

public class Interface {
	
	//测试用的main方法
	public static void main(String[] args) {
		//Man man = new Man("man"); //接口不能实例化对象
		
		//chinese和american都是Man，但具体是哪个子类在new时才确定
		Man2 chinese, american;
		
		chinese = new Chinese2("张三");		
		american = new American2("Mike");
		
		chinese.sayMyName();
		chinese.walk();
		
		american.sayMyName();
		american.walk();
	}
}

interface Man2 {
	
	//接口上定义的属性默认是静态的，所以不能不初始化
	//String name;

	//抽象方法，留给子孙实现
	public void sayMyName();
	
	//普通的方法
	void walk();
	
	//接口不允许声明protected或private的方法
	//protected void func1();	
	//private void func1();
}

class Chinese2 implements Man2 {
	
	String name;

	Chinese2(String name) {
		this.name = name;
	}
	
	//接口声明的方法都是外界可以使用的，默认是public，所以实现时必须有public字样
	public void sayMyName() {
		System.out.println("我的名字叫" + name);
	}

	public void walk() {
		System.out.println(name + "走了几步");
	}
	
}

class American2 implements Man2 {

	String name;
	
	American2(String name) {
		this.name = name;
	}

	public void sayMyName() {
		System.out.println("my name is " + name);
	}

	public void walk() {
		System.out.println(name + "走了几步");
	}
	
}