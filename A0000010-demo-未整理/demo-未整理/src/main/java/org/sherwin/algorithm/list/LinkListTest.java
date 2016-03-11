package org.sherwin.algorithm.list;

import junit.framework.TestCase;

public class LinkListTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	//test����ձ�
	public void testClear() throws IndexOutOfBoundsException {
		List list = this.createList();
		list.clear();
		this.assertEquals(list.length(), 0);
		
		list = this.addHumans(3, list);		
		this.assertEquals(list.length(), 3);
		
		list.clear();
		this.assertEquals(list.length(), 0);
	}

	//ɾ����i��Ԫ��
	public void testDelete() throws IndexOutOfBoundsException {
		List list = this.createList();
		
		Human e = null;
		try {
			e = list.delete(1);
			fail();
		} catch(IndexOutOfBoundsException e1) {
			this.assertNull(e);
		}

		this.addHumans(10, list);
		
		e = list.delete(1);
		this.assertNotNull(e);
		
		e = list.delete(3);
		this.assertNotNull(e);
		
		e = list.delete(8);
		this.assertNotNull(e);
		
		try {
			e = null;
			e = list.delete(8);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertNull(e);
		}
		
		try {
			e = null;
			e = list.delete(20);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertNull(e);
		}

		try {
			e = null;
			e = list.delete(0);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertNull(e);
		}
	}

	//ȡ���е�i��Ԫ��
	public void testGet() throws IndexOutOfBoundsException {
		List list = this.createList();
		
		Human e = null;
		try {
			e = list.get(1);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertNull(e);
		}
		
		this.addHumans(10, list);
		
		e = list.get(1);
		this.assertNotNull(e);
		
		e = list.get(3);
		this.assertNotNull(e);
		
		e = list.get(10);
		this.assertNotNull(e);
		
		try {
			e = null;
			e = list.get(11);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertNull(e);
		}
		
		try {
			e = null;
			e = list.get(20);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertNull(e);
		}

		try {
			e = null;
			e = list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertNull(e);
		}
	}

	//�ڵ�i��λ�ò���Ԫ��
	public void testInsert() throws IndexOutOfBoundsException {
		List list = this.createList();
		
		Human e = new Human(1,1);
		try {
			list.insert(0, e);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertEquals(0, list.length());
		}
		
		list.insert(1, e);
		this.assertEquals(1, list.length());
		
		e = new Human(2,1);
		try {			
			list.insert(3, e);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertEquals(1, list.length());
		}

		try {			
			list.insert(20, e);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			this.assertEquals(1, list.length());
		}
		
		list.insert(2, e);
		this.assertEquals(2, list.length());
		
		e = new Human(3,1);
		list.insert(2, e);
		this.assertEquals(3, list.length());
		
		e = new Human(4,1);
		list.insert(2, e);
		this.assertEquals(4, list.length());
	}

	//test�����п�
	public void testIsEmpty() throws IndexOutOfBoundsException {
		List list = this.createList();
		boolean empty = list.isEmpty();
		this.assertEquals(true, empty);
		
		Human e = new Human(1,1);
		list.insert(1, e);
		empty = list.isEmpty();
		this.assertEquals(false, empty);
	}

	//���
	public void testLength() throws IndexOutOfBoundsException {
		List list = this.createList();
		this.assertEquals(0, list.length());
		
		Human e = new Human(1,1);
		list.insert(1, e);
		this.assertEquals(1, list.length());
	}

	//��Ԫ��λ��
	public void testLocate() throws IndexOutOfBoundsException {
		List list = this.createList();
		list = this.addHumans(10, list);
		Human e = list.get(3);
		int pos = list.locate(e);
		this.assertEquals(3, pos);
		
		e = new Human(11,1);
		pos = list.locate(e);
		this.assertEquals(-1, pos);
	}

	//��Ԫ�غ��
	public void testNext() throws IndexOutOfBoundsException {
		List list = this.createList();
		list = this.addHumans(10, list);
		Human e1 = list.get(8);
		Human e2 = list.get(9);
		e1 = list.next(e1);
		this.assertEquals(e1, e2);
		
		e1 = list.get(10);
		e1 = list.next(e1);
		this.assertNull(e1);
	}

	//��Ԫ��ǰ��
	public void testPrior() throws IndexOutOfBoundsException {
		List list = this.createList();
		list = this.addHumans(10, list);
		Human e1 = list.get(2);
		Human e2 = list.get(1);
		e1 = list.prior(e1);
		this.assertEquals(e1, e2);
		
		e1 = list.get(1);
		e1 = list.prior(e1);
		this.assertNull(e1);
	}

	//������
	public void testTravel() throws IndexOutOfBoundsException {
		List list = this.createList();
		list = this.addHumans(5, list);
		
		list.travel(new TravelVisitor() {
			public void visit(Human e) {
				TestCase.assertNotNull(e);
			}
		});
	}

	protected List createList() {
		return new LinkList();
	}
	
	protected List addHumans(int count, List list) throws IndexOutOfBoundsException {
		if (null == list) {
			list = this.createList();
		}
		int length = list.length();
		for (int i=1; i<=count; i++) {
			Human e = new Human(length+i,length+i);
			list.insert(length+i, e);
		}
		return list;
	}
}
