package com.sherwin.question.string;

/**
 * 字符串创建的机制
 *
 */
public class StringTest {
	public static void main(String[] args) {
		String a = "ab";// 创建了一个对象，并加入字符串池中，对象引用赋给a
		System.out.println("String a = \"ab\";");
		String b = "cd";// 创建了一个对象，并加入字符串池中，对象引用赋给b
		System.out.println("String b = \"cd\";");
		String c = "abcd";// 创建了一个对象，并加入字符串池中，对象引用赋给c
		System.out.println("String c = \"abcd\";");
		
		String d = "ab" + "cd";
		if (d == c) {
			System.out.println("\"ab\"+\"cd\" 是字符串池中已存在的对象，引用赋给d");
		}
		else {
			System.out.println("\"ab\"+\"cd\" 现在新建并加入字符串池中，引用赋给d");
		}

		String e = a + "cd";
		if (e == c) {
			System.out.println(" a  +\"cd\" 是字符串池中已存在的对象，引用赋给e");
		}
		else {
			System.out.println(" a  +\"cd\" 现在新建并加入字符串池中，引用赋给e");
		}

		String f = "ab" + b;
		if (f == c) {
			System.out.println("\"ab\"+ b   是字符串池中已存在的对象，引用赋给f");
		}
		else {
			System.out.println("\"ab\"+ b   现在新建并加入字符串池中，引用赋给f");
		}

		String g = a + b;
		if (g == c) {
			System.out.println(" a  + b   是字符串池中已存在的对象，引用赋给g");
		}
		else {
			System.out.println(" a  + b   现在新建并加入字符串池中，引用赋给g");
		}
	}
}
