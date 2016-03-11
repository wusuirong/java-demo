package org.sherwin.algorithm.stack;

import org.sherwin.algorithm.list.Human;
import org.sherwin.algorithm.list.TravelVisitor;

import junit.framework.TestCase;

public class StackTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testClear() {
		Stack stack = createStack();
		
		Human h = new Human(1,1);
		stack.push(h);
		assertEquals(false, stack.isEmpty());
		
		h = new Human(2,2);
		stack.push(h);
		assertEquals(false, stack.isEmpty());
		
		stack.clear();
		assertEquals(true, stack.isEmpty());
	}

	public void testGetTop() {
		Stack stack = createStack();
		
		Human h = null, top = null;
		top = stack.getTop();
		this.assertNull(top);
		
		for (int i=0; i<20; i++) {
			h = new Human(i,i);
			stack.push(h);
			top = stack.getTop();
			this.assertEquals(h, top);
		}
		
		for (int i=19; i>=0; i--) {
			h = new Human(i,i);
			top = stack.getTop();
			stack.pop();
			this.assertEquals(h, top);
		}
	}

	public void testIsEmpty() {
		this.testClear();
	}

	public void testLength() {
		Stack stack = createStack();
		
		assertEquals(0, stack.length());
		
		Human h = new Human(1,1);
		stack.push(h);
		assertEquals(1, stack.length());
		
		h = new Human(2,2);
		stack.push(h);
		assertEquals(2, stack.length());
		
		stack.clear();
		assertEquals(0, stack.length());
	}

	public void testPop() {
		Stack stack = createStack();
		
		Human h = null, top = null;
		top = stack.pop();
		this.assertNull(top);
		
		for (int i=0; i<20; i++) {
			h = new Human(i,i);
			stack.push(h);
		}
		
		for (int i=19; i>=0; i--) {
			h = new Human(i,i);
			top = stack.pop();
			this.assertEquals(h, top);
		}
	}

	public void testPush() {
		Stack stack = createStack();
		
		Human h = null, top = null;
		
		for (int i=0; i<20; i++) {
			h = new Human(i,i);
			stack.push(h);
			top = stack.getTop();
			this.assertEquals(h, top);
		}
	}

	public void testTravel() {
		Stack stack = createStack();
		
		Human h = null;
		
		for (int i=0; i<10; i++) {
			h = new Human(i,i);
			stack.push(h);
		}
		
		stack.travel(new TravelVisitor() {

			public void visit(Human e) {
				TestCase.assertNotNull(e);
			}
			
		});
	}
	
	private Stack createStack() {
		return new ArrayStack();
	}

}
