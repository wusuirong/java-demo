package org.sherwin.algorithm.stack;

import org.sherwin.algorithm.list.Human;
import org.sherwin.algorithm.list.TravelVisitor;

public class ArrayStack implements Stack {
	
	private static final int INIT_SIZE = 10;//��ʼ�����С
	private static final int INCREMENT = 2;//������С
	
	private Human[] bottom;//ջ��
	private int top;//ջ��λ��0��ʾջΪ��
	private int size;//��ǰ�ռ��С

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
			//��Ϊ�ڲ������0��ʼ���㣬��top��1��ʼ
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
			//��Ϊ�ڲ������0��ʼ���㣬��top��1��ʼ
			top--;
			return bottom[top];
		} else {
			return null;
		}
	}

	public void push(Human e) {
		if (top == size) {
			//����copy
			Human[] tmp = new Human[size + INCREMENT];
			System.arraycopy(bottom, 0, tmp, 0, size);
			bottom = tmp;
			size += INCREMENT;
		}
		bottom[top] = e;
		top++;//��Ϊ�ڲ������0��ʼ���㣬��top��1��ʼ
	}

	public void travel(TravelVisitor visitor) {
		for (int i=0; i<top; i++) {
			visitor.visit(bottom[i]);
		}
	}

}
