package com.sherwin.question.string;

/**
 * 字符串的intern方法
 * String b = a.intern();
 * 是判断a引用的字符串对象A是否在字符串池中，是则返回字符串对象A的引用给b
 * 否则在字符串池中创建一个和a引用的对象A一样的字符串对象B，把B的引用返回给b
 *
 */
public class StringInternTest {
	public static void main(String[] args) {
		// 使用char数组来初始化a，避免在a被创建之前字符串池中已经存在了值为"abcd"的对象
		String a = new String(new char[] { 'a', 'b', 'c', 'd' });
		String b = a.intern();
		if (b == a) {
			System.out.println("b引用的是字符串池中已存在的对象");
		} else {
			System.out.println("b引用的是新建的对象");
		}

		String c = a.intern();
		if (c == a) {
			System.out.println("c引用的是字符串池中已存在的对象");
		} else {
			System.out.println("c引用的是新建的对象");
		}
		
		String d = b.intern();
		if (d == b) {
			System.out.println("d引用的是字符串池中已存在的对象");
		} else {
			System.out.println("d引用的是新建的对象");
		}
	}
}
