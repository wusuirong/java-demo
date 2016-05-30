package org.danny.demo;

public class IsAssignableFrom和InstanceOf的测试 {

	public static void main(String[] args) {
		String str = "";
        Object o = new Object();
        System.out.println("str是Object类型：" + (str instanceof Object));
        System.out.println("o是String的子类：" + String.class.isInstance(o));
        System.out.println("str是String的子类：" +String.class.isInstance(str));
        System.out.println("String是Object的父类：" + String.class.isAssignableFrom(Object.class));
        System.out.println("Object是Object相同的类：" + Object.class.isAssignableFrom(Object.class));
        System.out.println("Object是String的父类：" + Object.class.isAssignableFrom(String.class));
	}
}
