package com.sherwin.examples.innerclass;

public class Goods {
	private int valueRate = 2;

	private class PrivateInnerClass implements Contents {
		private int i = 11 * valueRate;

		public int value() {
			return i;
		}
	}

	protected class ProtectedInnerClass implements Destination {
		private String label;

		private ProtectedInnerClass(String whereTo) {
			label = whereTo;
		}

		public String readLabel() {
			return label;
		}
	}
	
	public class PublicInnerClass {
		int valueRate = 3;
		
		public int count() {
			//内部类的变量和外部类变量重复的场景
			
			/*
			 * 内部类可以访问外部类的变量，所以它生成时会持有外部类对象的引用
			 * 这就是生成内部类对象前必须生成外部类对象的原因
			 */			
			return valueRate + Goods.this.valueRate;
		}
	}
	
	static public class StaticInnerClass {
		static public int value = 3;
	}

	public Destination dest(String s) {
		return new ProtectedInnerClass(s);
	}

	public Contents cont() {
		return new PrivateInnerClass();
	}
	
	//使用匿名类
	public Contents getAnonymousClass() {
		return new Contents() {
			public int value() {
				return 0;
			}
		};
	}
}

class TestGoods {
	public static void main(String[] args) {
		Goods p = new Goods();
		//内部类可以隐藏实现
		Contents c = p.cont();
		Destination d = p.dest("Beijing");
		
		//内部类对象拥有外部类对象的引用，所以必须先有外部类对象
		Goods.PublicInnerClass innerClass = p.new PublicInnerClass();
		
		int val = Goods.StaticInnerClass.value;
		
		//静态内部类对象没有外部类对象引用，所以不需要有外部类对象
		Goods.StaticInnerClass c1 = new Goods.StaticInnerClass();
		
		c = p.getAnonymousClass();
		
		//总结:通过内部类和接口，java可以实现多继承
	}
}