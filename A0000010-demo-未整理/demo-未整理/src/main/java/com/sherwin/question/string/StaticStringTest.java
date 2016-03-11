package com.sherwin.question.string;

/**
 * 如果编译器在编译期间能确定常量的值，则就能放进字符串池中
 *
 */
public class StaticStringTest {
	// 常量A
	public static final String A = "ab";

	// 常量B
	public static final String B = "cd";
	
	// 常量C
	public static final String C;

	// 常量D
	public static final String D;
	
	static {
		C = "ef";
		D = "gh";
	}

	public static void main(String[] args) {
		// 将两个常量用+连接对s进行初始化
		String s = A + B;
		String t = "abcd";
		if (s == t) {
			System.out.println("s等于t，它们是同一个对象");
		} else {
			System.out.println("s不等于t，它们不是同一个对象");
		}
		
		String p = C + D;
		String q = "efgh";
		if (p == q) {
			System.out.println("p等于q，它们是同一个对象");
		} else {
			System.out.println("p不等于q，它们不是同一个对象");
		}
	}
}
