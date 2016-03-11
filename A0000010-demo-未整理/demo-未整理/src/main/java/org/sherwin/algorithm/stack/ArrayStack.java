package org.sherwin.algorithm.stack;

import org.sherwin.algorithm.list.Human;
import org.sherwin.algorithm.list.TravelVisitor;

public class ArrayStack implements Stack {
	
	private static final int INIT_SIZE = 10;//初始分配大小
	private static final int INCREMENT = 2;//增量大小
	
	private Human[] bottom;//栈底
	private int top;//栈顶位序，0表示栈为空
	private int size;//当前空间大小

	public ArrayStack() {
		bottom = new Human[INIT_SIZE];
		top = 0;
		size = bottom.length;
	}
	
	public void clear() {
		top = 0;
	}

	public Human getTop() {
		if (top > 0) {
			//因为内部数组从0开始计算，而top从1开始
			return bottom[top - 1];
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		return 0 == top;
	}

	public int length() {
		return top;
	}

	public Human pop() {
		if (top > 0) {
			//因为内部数组从0开始计算，而top从1开始
			top--;
			return bottom[top];
		} else {
			return null;
		}
	}

	public void push(Human e) {
		if (top == size) {
			//数组copy
			Human[] tmp = new Human[size + INCREMENT];
			System.arraycopy(bottom, 0, tmp, 0, size);
			bottom = tmp;
			size += INCREMENT;
		}
		bottom[top] = e;
		top++;//因为内部数组从0开始计算，而top从1开始
	}

	public void travel(TravelVisitor visitor) {
		for (int i=0; i<top; i++) {
			visitor.visit(bottom[i]);
		}
	}

}
