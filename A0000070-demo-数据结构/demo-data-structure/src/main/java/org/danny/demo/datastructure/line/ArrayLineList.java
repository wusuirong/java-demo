package org.danny.demo.datastructure.line;

import java.util.Comparator;

/**
 * 用数组实现的线性表
 */
public class ArrayLineList implements LineList {
	
	private Object[] objs;//内部数组
	
	private int elementCounts = 0;//当前元素个数
	
	private static final int arrayInitSize = 10;//数组初始大小
	
	private static final int arrayIncrementSize = 10;//每次增量大小
	
	public ArrayLineList() {
		objs = new Object[arrayInitSize];
		elementCounts = 0;
	}

	public Object getElement(int pos) {
		if (pos < 0 || pos > elementCounts - 1) {
			throw new IndexOutOfBoundsException();
		}
		return objs[pos];
	}
	
	public Object deleteElement(int pos) throws IndexOutOfBoundsException {
		if (pos < 0 || pos > elementCounts - 1) {
			throw new IndexOutOfBoundsException();
		}
		Object o = objs[pos];
		//把后面的元素移上来
		for (int i=pos; i<objs.length-1; i++) {
			objs[i] = objs[i+1];
			objs[i+1] = null;
		}
		elementCounts--;
		
		return o;
	}

	public boolean insertElement(int pos, Object o)
			throws IndexOutOfBoundsException {
		if (pos < 0 || pos > elementCounts - 1) {
			throw new IndexOutOfBoundsException();
		}
		//如果数组满则要申请新空间并把旧数据复制过去
		if (elementCounts == objs.length) {
			Object[] tmp = new Object[objs.length + arrayIncrementSize];
			for (int i=0; i<pos; i++) {
				tmp[i] = objs[i];
			}
			objs[pos] = o;
			for (int i=pos; i<elementCounts; i++) {
				tmp[i+1] = objs[i];
			}
			objs = tmp;
		} else {//否则移动后面元素，直接插入
			for (int i=elementCounts-1; i>=pos; i--) {
				objs[i+1] = objs[i];
			}
			objs[pos] = o;
		}		
		return true;
	}
}
