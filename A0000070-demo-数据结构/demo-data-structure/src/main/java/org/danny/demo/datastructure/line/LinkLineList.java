package org.danny.demo.datastructure.line;

import java.util.Comparator;

/**
 * 用链表实现的线性表
 * 有头节点的
 */
public class LinkLineList implements LineList {

	private LinkNode head;
	private int elementCount;//元素下标为0到elementCount-1
	
	public LinkLineList() {
		head = new LinkNode(null, null);
		elementCount = 0;
	}

	/* 
	 * 初始化：指针指向第一个元素节点（头节点的下一个节点），计数器归零
	 * 
	 * 做真值表
	 * 当前节点是否为空	计数器和目标位置大小比较	状态描述
	 * 通过真值表就知道哪些情况是异常
	 * 开始移动：只要当前节点非空且计数器未到达目标就可以移动
	 * 
	 * 移动结束后判断是否处于异常状态
	 * 
	 * 返回目标节点
	 */
	public Object getElement(int pos) {
		LinkNode curr = head.next;
		int idx = 0;
		
		while ((null != curr) && (idx < pos)) {
			curr = curr.next;
			idx++;
		}
		
		if ((null == curr) || (idx > pos)) {
			throw new IndexOutOfBoundsException();
		}
		return curr.element;
	}

	/* 
	 * 类似删除算法，但插入时是插入元素前还是元素后呢？
	 * 如果是之后，则用户想插入位置3的，但却插到位置3的元素后面。
	 * 所以是插入在指定位置元素的前面。
	 * 和删除算法类似，需要使用前驱节点指针，这样插入时只要插在前驱后面即可。
	 * 
	 * 初始化：前驱指针指向头节点，计数器是用来指明前驱指针位置，所以设为-1
	 * 
	 * 做真值表
	 * 前驱节点是否为空	计数器和目标前一个位置大小比较	状态描述
	 * 通过真值表就知道哪些情况是异常
	 * 开始移动：只要前驱节点非空且计数器未到达目标前一个位置就可以移动
	 * 
	 * 移动结束后判断是否处于异常状态
	 * 
	 * 插入节点
	 */
	public boolean insertElement(int pos, Object o)
			throws IndexOutOfBoundsException {
		LinkNode pre = head;
		int idx = -1;
		/*
		 * 列出pre和idx的真值表，确定循环条件
		 */
		while ((null != pre) && (idx < pos-1)) {
			pre = pre.next;
			idx++;
		}
		
		//根据真值表确定错误情况
		if ((null == pre) || (idx > pos-1)) {
			throw new IndexOutOfBoundsException();
		}

		//插入操作
		LinkNode elmt = new LinkNode(o,pre.next);
		pre.next = elmt;
		elementCount++;
		
		return true;
	}


	/* 
	 * 类似查找算法，但删除时要知道前驱节点，所以我们移动时需要一个当前节点指针，一个前驱节点指针
	 * 但进一步分析，前驱节点和当前节点永远是一前一后的关系，所以只要有前驱节点指针就可以了。
	 * 
	 * 初始化：前驱指针指向头节点，计数器是用来指明前驱指针位置，所以设为-1
	 * 
	 * 做真值表
	 * 前驱节点的next域是否为空	计数器和目标前一个位置大小比较	状态描述
	 * 通过真值表就知道哪些情况是异常
	 * 开始移动：只要前驱节点的next域非空且计数器未到达目标前一个位置就可以移动
	 * 
	 * 移动结束后判断是否处于异常状态
	 * 
	 * 删除节点
	 */
	public Object deleteElement(int pos) {
		LinkNode pre = head;
		int idx = -1;
		/*
		 * 列出pre.next和idx的真值表，确定循环条件
		 */
		while ((null != pre.next) && (idx < pos-1)) {
			pre = pre.next;
			idx++;
		}
		
		//根据真值表确定错误情况
		if ((null == pre.next) || (idx > pos-1)) {
			throw new IndexOutOfBoundsException();
		}

		//删除操作
		LinkNode curr = pre.next;
		pre.next = curr.next;
		curr.next = null;
		elementCount--;
		
		return curr;
	}
	
	/**
	 * 因为使用者只关心数据元素本身，所以如果不想外部知道节点实现，可用内部类实现。
	 */
	private class LinkNode {
		private Object element;
		private LinkNode next;
		
		public LinkNode(Object element, LinkNode next) {
			this.element = element;
			this.next = next;
		}
		
		public Object getElement() {
			return element;
		}
		public void setElement(Object element) {
			this.element = element;
		}
		public LinkNode getNext() {
			return next;
		}
		public void setNext(LinkNode next) {
			this.next = next;
		}
	}
}
