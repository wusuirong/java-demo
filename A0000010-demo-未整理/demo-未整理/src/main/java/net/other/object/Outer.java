package net.other.object;

public class Outer {
	private int data = 0;

	public Inner in = new Inner();// 编译器把它替换为以外部类对象为参数的构造函数Outer$Inner(Outer o)

	class Inner {

		// 编译器会生成一个以外部类对象为参数的构造函数Outer$Inner(Outer o)，所以不怕被声明为private
		private Inner() {
		}

		void print() {
			// 内部类访问外部私有数据域
			System.out.println(data);
		}
	}
}
