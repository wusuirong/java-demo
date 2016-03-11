package org.danny.demo.datastructure.stack;

public class ArrayStack implements Stack {
	
	private Object[] array;
	
	private int top;
	
	private static final int INIT_SIZE = 10;
	
	private static final int INCREMENT_SIZE = 10;
	
	public ArrayStack() {
		array = new Object[INIT_SIZE];
		top = 0;
	}

	public Object pop() {
		if (0 == top) {
			return null;
		} else {
			top--;
			return array[top];
		}
	}

	public void push(Object o) {
		if (top >= array.length) {
			Object[] tmp = new Object[array.length + INCREMENT_SIZE];
			for (int i=0; i<array.length; i++) {
				tmp[i] = array[i];
			}
		}
		array[top] = o;
		top++;
	}

}
