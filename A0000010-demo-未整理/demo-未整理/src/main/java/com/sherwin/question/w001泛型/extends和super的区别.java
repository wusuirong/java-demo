package com.sherwin.question.w001泛型;

import java.util.ArrayList;
import java.util.List;

public class extends和super的区别 {

	static class Food{}
	static class Fruit extends Food{}
	static class Apple extends Fruit{}
	static class RedApple extends Apple{}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * ? 通配符类型
		 * <? extends T> 表示类型的上界，表示参数化类型的可能是T 或是 T的子类
		 * <? super T> 表示类型下界（Java Core中叫超类型限定），表示参数化类型是此类型的超类型（父类型），直至Object
		 */
		
		extends测试(new ArrayList<Apple>());
		
		super测试(new ArrayList<Fruit>());
		/*
		 * 小结
		 * extends 可用于返回类型限定，不能用于参数类型限定。
		 * super 可用于参数类型限定，不能用于返回类型限定。
		 * 带有super超类型限定的通配符可以向泛型对易用写入，带有extends子类型限定的通配符可以向泛型对象读取。
		 */
	}
	
	static void extends测试(List<? extends Fruit> flist) {
		// 运行时的实际入参可能是苹果类型，但编译器不知道，所以如果允许放对象，放了个橙子对象就会导致运行时出错
		// 所以编译器不允许往入参放对象
		// complie error:
		// flist.add(new Apple());
		// flist.add(new Fruit());
		// flist.add(new Object());
		flist.add(null); // 可以添加null,因为null 可以表示任何类型。

		//而编译器知道里面的都属于水果类型，所以允许从里面取数据，数据都以水果形式返回
		Fruit fruit = flist.get(0);
		Apple apple = (Apple)flist.get(0);
	}
	
	static void super测试(List<? super Fruit> flist) {
		// 运行时的实际入参可能是食物类型，往里面放食物的子类是可以的，这是泛型最普通的用法，就不解释了
		flist.add(new Fruit());
		flist.add(new Apple());
		flist.add(new RedApple());
		
		
		Object o = flist.get(0);
		// 因为里面可能是食物类，所以
		// compile error:
		//Fruit item = flist.get(0);
	}
}

