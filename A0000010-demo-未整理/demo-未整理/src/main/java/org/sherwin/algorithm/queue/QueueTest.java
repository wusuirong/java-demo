package org.sherwin.algorithm.queue;

import org.sherwin.algorithm.list.Human;
import org.sherwin.algorithm.list.TravelVisitor;

import junit.framework.TestCase;

public class QueueTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testClear() {
		Queue queue = createQueue();
		
		Human h = new Human(1,1);
		queue.enQueue(h);
		assertEquals(false, queue.isEmpty());
		
		h = new Human(2,2);
		queue.enQueue(h);
		assertEquals(false, queue.isEmpty());
		
		queue.clear();
		assertEquals(true, queue.isEmpty());
	}

	public void testGetHead() {
		Queue queue = createQueue();
		
		Human h = null, head = null;
		head = queue.getHead();
		this.assertNull(head);
		
		for (int i=0; i<20; i++) {
			h = new Human(i,i);
			queue.enQueue(h);
			head = queue.getHead();
			this.assertEquals(h, head);
		}
		
		for (int i=19; i>=0; i--) {
			h = new Human(i,i);
			head = queue.getHead();
			queue.deQueue();
			this.assertEquals(h, head);
		}
	}

	public void testIsEmpty() {
		this.testClear();
	}

	public void testLength() {
		Queue queue = createQueue();
		
		assertEquals(0, queue.length());
		
		Human h = new Human(1,1);
		queue.enQueue(h);
		assertEquals(1, queue.length());
		
		h = new Human(2,2);
		queue.enQueue(h);
		assertEquals(2, queue.length());
		
		queue.clear();
		assertEquals(0, queue.length());
	}

	public void testDeQueue() {
		Queue queue = createQueue();
		
		Human h = null, head = null;
		head = queue.deQueue();
		this.assertNull(head);
		
		for (int i=0; i<20; i++) {
			h = new Human(i,i);
			queue.enQueue(h);
		}
		
		for (int i=19; i>=0; i--) {
			h = new Human(i,i);
			head = queue.deQueue();
			this.assertEquals(h, head);
		}
	}

	public void testEnQueue() {
		Queue queue = createQueue();
		
		Human h = null, head = null;
		
		for (int i=0; i<20; i++) {
			h = new Human(i,i);
			queue.enQueue(h);
			head = queue.getHead();
			this.assertEquals(h, head);
		}
	}

	public void testTravel() {
		Queue queue = createQueue();
		
		Human h = null;
		
		for (int i=0; i<10; i++) {
			h = new Human(i,i);
			queue.enQueue(h);
		}
		
		queue.travel(new TravelVisitor() {

			public void visit(Human e) {
				TestCase.assertNotNull(e);
			}
			
		});
	}
	
	private Queue createQueue() {
		return new ArrayQueue();
	}

}
