package org.danny.demo.datastructure.line;

import java.util.Comparator;

public interface LineList {
	
	/*
	 * 取元素
	 * 输入：下标（0 <= pos <= 元素个数-1）
	 * 功能：
	 * 输出：对应元素
	 */
	public Object getElement(int pos);
	
	/*
	 * 插入元素
	 * 输入：元素和插入下标（0 <= pos <= 元素个数）
	 * 功能：把元素插入到下标处
	 * 输出：成功/失败
	 */
	public boolean insertElement(int pos, Object o) throws IndexOutOfBoundsException;
	
	/*
	 * 删除元素
	 * 输入：下标（0 <= pos <= 元素个数-1）
	 * 功能：在表中删除对应元素
	 * 输出：返回这个元素
	 */
	public Object deleteElement(int pos) throws IndexOutOfBoundsException;

}
