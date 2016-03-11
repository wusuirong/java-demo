package org.sherwin.algorithm.list;

public class Human {
	
	public int id;
	public int age;
	public boolean isMan;
	
	public Human next;//������ʹ�õ�ָ��
	
	public Human(int id, int age, boolean isMan) {
		this.id = id;
		this.age = age;
		this.isMan = isMan;
		this.next = null;
	}
	
	public Human(int id, int age) {
		this(id, age, true);
	}
	
	/**
	 * ͨ��id�ж�����human�Ƿ����
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Human)) {
			return false;
		}
		return (((Human)o).id == this.id);
	}
}
