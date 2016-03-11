package com.sherwin.examples.basics;

public class AbstractClass {
	
	//测试用的main方法
	public static void main(String[] args) {
		//Man man = new Man("man"); //抽象类不能实例化对象
		
		//chinese和american都是Man，但具体是哪个子类在new时才确定
		Man chinese, american;
		
		chinese = new Chinese("张三");		
		american = new American("Mike");
		
		chinese.sayMyName();
		chinese.walk();
		
		american.sayMyName();
		american.walk();
	}
}

abstract class Man {
	
	String name;//名称
	
	//带参数的构造函数
	Man(String name) {
		this.name = name;
	}
	
	//抽象方法，留给子孙实现
	abstract void sayMyName();
	
	//普通的方法
	void walk() {
		System.out.println(name + "走了几步");
	}
}

class Chinese extends Man {

	Chinese(String name) {
		//调用父类的构造方法初始化父类的属性
		super(name);
	}

	void sayMyName() {
		System.out.println("我的名字叫" + name);
	}
	
}

class American extends Man {

	American(String name) {
		//调用父类的构造方法初始化父类的属性
		super(name);
	}

	void sayMyName() {
		System.out.println("my name is " + name);
	}
	
}