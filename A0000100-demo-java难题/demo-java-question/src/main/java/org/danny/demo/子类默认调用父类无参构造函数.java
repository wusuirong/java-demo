package org.danny.demo;

public class 子类默认调用父类无参构造函数 {

	public static void main(String[] args) {
		无参构造的子类 child = new 无参构造的子类();
		System.out.println("子类默认会调用父类的无参构造函数");

		有参构造父类的子类 child2 = new 有参构造父类的子类();
		System.out.println("如果父类没有无参构造函数，子类的构造函数必须显式调用父类的构造函数");
	}
}

class 无参构造的父类 {
	public 无参构造的父类() {
		System.out.println("父类构建");
	}
}

class 无参构造的子类 extends 无参构造的父类 {
	public 无参构造的子类() {
		System.out.println("子类构建");
	}
}

class 有参构造的父类 {
	public 有参构造的父类(int i) {
		System.out.println("有参构造的父类构建");
	}
}

class 有参构造父类的子类 extends 有参构造的父类 {
	public 有参构造父类的子类() {
		super(1); // 如果这句注释掉编译会报错
		System.out.println("有参构造父类的子类构建");
	}
}