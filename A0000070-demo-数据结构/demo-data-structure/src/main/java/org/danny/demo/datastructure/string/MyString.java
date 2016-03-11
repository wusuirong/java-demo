package org.danny.demo.datastructure.string;

public interface MyString {
	
	/**
	 * 字符串连接
	 * 输入：字符串1，字符串2
	 * 功能：连接成新字符串
	 * 输出：连接好的字符串
	 */
	public MyString concat(MyString str);
	
	/**
	 * 求子串位置
	 * 输入：母串，模式串
	 * 功能：求模式串在母串的起始位置
	 * 输出：返回模式串在母串的起始位置
	 */
	public int indexOf(MyString str);

}
