package org.sherwin.algorithm.queue;

import org.sherwin.algorithm.list.Human;
import org.sherwin.algorithm.list.TravelVisitor;

public interface Queue {

	/**
	 * 说明：清空队列<p>
	 * 条件：<p>
	 * 结果：队列长为0<p>
	 */
	void clear();
	
	/**
	 * 说明：队列判空<p>
	 * 条件：<p>
	 * 结果：队列不为空返回true，为空返回false<p>
	 */
	boolean isEmpty();
	
	/**
	 * 说明：求队列长<p>
	 * 条件：<p>
	 * 结果：返回元素个数<p>
	 */
	int length();
	
	/**
	 * 说明：入队列<p>
	 * 条件：<p>
	 */
	void enQueue(Human e);
	
	/**
	 * 说明：出队列<p>
	 * 条件：<p>
	 */
	Human deQueue();
	
	/**
	 * 说明：取队头元素<p>
	 * 条件：<p>
	 */
	Human getHead();

	/**
	 * 说明：遍历队列<p>
	 * 条件：<p>
	 * 结果：<p>
	 */
	void travel(TravelVisitor visitor);
}