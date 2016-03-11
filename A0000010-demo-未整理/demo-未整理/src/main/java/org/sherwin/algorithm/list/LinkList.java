package org.sherwin.algorithm.list;

/**
 * ��ͷ�ڵ�ĵ�����
 */
public class LinkList implements List {
	
	private Human head;//ͷ�ڵ㲻������ݣ���Ϊ���㷨�жϵķ���
	
	public LinkList() {
		head = new Human(0,0);
	}

	public void clear() {
		while(null != head.next) {
			Human e = head.next;
			head.next = e.next;
			e.next = null;
		}
	}

	public Human delete(int i) throws IndexOutOfBoundsException {
		Human current = this.head;//ָ���ͷָ�����
		int j = 0;//��������0��ʼ

		while (current.next != null && j < i - 1) {
			current = current.next;
			j++;
		}
		
		if (current.next != null && j == i - 1) {
			Human e = current.next;
			current.next = current.next.next;
			return e;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public Human get(int i) throws IndexOutOfBoundsException {
		Human current = this.head.next;//ָ��ӵ�һ��Ԫ�س���
		int j = 1;//��������1��ʼ

		while (current != null && j < i) {
			current = current.next;
			j++;
		}
		
		if (current != null && j == i) {
			return current;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public void insert(int i, Human e) throws IndexOutOfBoundsException {
		Human current = this.head;//ָ���ͷָ�����
		int j = 0;//��������0��ʼ

		while (current != null && j < i - 1) {
			current = current.next;
			j++;
		}
		
		if (current != null && j == i - 1) {
			e.next = current.next;
			current.next = e;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public boolean isEmpty() {
		return null == head.next;
	}

	public int length() {
		Human current = head;
		int i = 0;
		while (null != current.next) {
			current = current.next;
			i++;
		}
		return i;
	}

	public int locate(Human e) {
		Human current = head.next;
		int i = 1;
		while (null != current && !current.equals(e)) {
			current = current.next;
			i++;
		}
		
		if (null != current && current.equals(e)) {
			return i;
		} else {
			return -1;
		}
	}

	public Human next(Human e) {
		Human current = head.next;
		while (null != current && !current.equals(e)) {
			current = current.next;
		}
		
		if (null != current && current.equals(e)) {
			return current.next;
		} else {
			return null;
		}
	}

	public Human prior(Human e) {
		Human current = head.next;
		
		while (null != current 
				&& null != current.next 
				&& !current.next.equals(e)) {
			current = current.next;
		}
		
		if (null != current 
				&& null != current.next
				&& current.next.equals(e)) {
			return current;
		} else {
			return null;
		}
	}

	public int size() {
		return this.length();
	}

	public void travel(TravelVisitor visitor) {
		Human current = head.next;
		
		while (null != current) {
			visitor.visit(current);
			current = current.next;
		}
	}

}

