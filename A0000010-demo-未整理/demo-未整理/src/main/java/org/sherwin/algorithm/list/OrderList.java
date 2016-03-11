package org.sherwin.algorithm.list;

import java.util.Vector;

public class OrderList implements List {
	
	private static final int INIT_SIZE = 10;//��ʼ�����С
	private static final int INCREMENT = 2;//������С
	
	private Human[] humans;//index��1��ʼ��length��ע�����ӿڵ��±��1��ʼ�����ڲ�������±��0��ʼ
	private int length;//��ǰ����
	private int size;//��ǰ�ռ��С
	
	public OrderList() {
		humans = new Human[INIT_SIZE];
		length = 0;
		size = humans.length;
	}

	public void clear() {
		length = 0;
	}

	public Human delete(int i) throws IndexOutOfBoundsException {
		if (i<1 || i>length) {
			throw new IndexOutOfBoundsException();
		}
		//ע�����ӿڵ��±��1��ʼ�����ڲ�������±��0��ʼ
		Human tmp = humans[i-1];
		for (int j=i; j<length; j++) {
			humans[j-1] = humans[j];
		}
		length--;
		return tmp;
	}

	public Human get(int i) throws IndexOutOfBoundsException {
		if (i<1 || i>length) {
			throw new IndexOutOfBoundsException();
		}
		return humans[i-1];
	}

	public void insert(int i, Human e) throws IndexOutOfBoundsException {
		if (i<1 || i>length+1) {
			throw new IndexOutOfBoundsException();
		}

		if (length == size) {
			//����copy
			Human[] tmp = new Human[size + INCREMENT];
			System.arraycopy(humans, 0, tmp, 0, size);
			humans = tmp;
			size += INCREMENT;
		}
		//ע�����ӿڵ��±��1��ʼ�����ڲ�������±��0��ʼ
		//j��iʹ�ô�1��ʼ���±귽ʽ
		for(int j=length; j>=i; j--) {
			humans[j] = humans[j-1];
		}
		humans[i-1] = e;
		length++;
	}

	public boolean isEmpty() {
		return 0 == length;
	}

	public int length() {
		return length;
	}
	
	public int size() {
		return size;
	}

	public int locate(Human e) {
		for (int i=1; i<=this.length; i++) {
			if (this.get(i).equals(e)) {
				return i;
			}
		}
		return -1;
	}

	public Human next(Human e) {
		int idx = this.locate(e);
		if (idx >= length) {
			return null;
		}
		return this.get(idx + 1);
	}

	public Human prior(Human e) {
		int idx = this.locate(e);
		if (idx <= 1) {
			return null;
		}
		return this.get(idx - 1);
	}

	public void travel(TravelVisitor visitor) {
		for(int i=1; i<=length; i++) {
			visitor.visit(this.get(i));
		}
	}
}
