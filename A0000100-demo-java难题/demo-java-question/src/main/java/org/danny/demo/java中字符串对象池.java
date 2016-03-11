package org.danny.demo;

public class java中字符串对象池 {

	// 程序入口
	public static void main(String[] args) {
		String s1 = "aa";
		String s2 = "aa";
		System.out.println("string比较");
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println("aa".hashCode());
		System.out.println("s1 == aa : " + (s1=="aa"));
		System.out.println("s1 == s2 : " + (s1==s2));
		
		Integer i1 = 1;
		Integer i2 = 1;
		System.out.println("int比较");
		System.out.println(i1.hashCode());
		System.out.println(i2.hashCode());
		System.out.println("i1 == i2 : " + (i1==i2));

	}

}
