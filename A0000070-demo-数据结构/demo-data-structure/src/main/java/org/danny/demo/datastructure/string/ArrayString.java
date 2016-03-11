package org.danny.demo.datastructure.string;

public class ArrayString implements MyString {
	
	private static final int INIT_SIZE = 255;
	
	private char[] array;
	
	public ArrayString(char[] chars) {
		array = new char[INIT_SIZE + 1];//第一个字符放长度
		int max = (chars.length > INIT_SIZE)?INIT_SIZE:chars.length;
		
		array[0] = (char)max;
		for (int i=1; i<INIT_SIZE+1; i++) {
			array[i] = chars[i-1];
		}
	}

	/* 
	 * m+n <= INIT_SIZE，复制s1和s2
	 * m < INIT_SIZE，复制s1，复制s2的INIT_SIZE - m
	 * m = INIT_SIZE，直接返回s1
	 */
	public MyString concat(MyString str) {
		//int len = (int)array[0] + (int)str[0];
		return null;
	}

	public int indexOf(MyString str) {
		// TODO Auto-generated method stub
		return 0;
	}

}
