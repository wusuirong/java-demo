package org.sherwin.algorithm.stack;

import org.sherwin.algorithm.list.Human;
import org.sherwin.algorithm.list.TravelVisitor;

public interface Stack {

	/**
	 * 说明：清空栈<p>
	 * 条件：<p>
	 * 结果：栈长为0<p>
	 */
	void clear();
	
	/**
	 * 说明：栈判空<p>
	 * 条件：<p>
	 * 结果：栈不为空返回true，为空返回false<p>
	 */
	boolean isEmpty();
	
	/**
	 * 说明：求栈长<p>
	 * 条件：<p>
	 * 结果：返回元素个数<p>
	 */
	int length();
	
	/**
	 * 说明：入栈<p>
	 * 条件：<p>
	 */
	void push(Human e);
	
	/**
	 * 说明：出栈<p>
	 * 条件：<p>
	 */
	Human pop();
	
	/**
	 * 说明：取栈顶元素<p>
	 * 条件：<p>
	 */
	Human getTop();

	/**
	 * 说明：遍历栈<p>
	 * 条件：<p>
	 * 结果：<p>
	 */
	void travel(TravelVisitor visitor);
}