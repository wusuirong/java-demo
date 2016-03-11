package org.sherwin.algorithm.list;

public interface List {
	
	//**********************表属性操作**********************

	/**
	 * 说明：清空表<p>
	 * 条件：<p>
	 * 结果：表长为0<p>
	 */
	void clear();
	
	/**
	 * 说明：表判空<p>
	 * 条件：<p>
	 * 结果：表不为空返回true，为空返回false<p>
	 */
	boolean isEmpty();
	
	/**
	 * 说明：求表长<p>
	 * 条件：<p>
	 * 结果：返回元素个数<p>
	 */
	int length();
	
	/**
	 * 说明：求表空间大小<p>
	 * 条件：<p>
	 * 结果：返回表大小<p>
	 */
	int size();

	//**********************元素操作**********************

	/**
	 * 说明：在第i个位置前插入元素<p>
	 * 条件：1<=i<=length()+1<p>
	 * 结果：插入元素后length+1<p>
	 */
	void insert(int i, Human e) throws IndexOutOfBoundsException;
	
	/**
	 * 说明：删除第i个元素<p>
	 * 条件：1<=i<=length()<p>
	 * 结果：删除元素后length-1<p>
	 */
	Human delete(int i) throws IndexOutOfBoundsException;
	
	//**********************元素访问**********************

	/**
	 * 说明：取表中第i个元素<p>
	 * 条件：1<=i<=length()<p>
	 * 结果：<p>
	 */
	Human get(int i) throws IndexOutOfBoundsException;
	
	/**
	 * 说明：求元素位置<p>
	 * 条件：<p>
	 * 结果：<p>
	 */
	int locate(Human e);
	
	/**
	 * 说明：求元素前驱<p>
	 * 条件：<p>
	 * 结果：<p>
	 */
	Human prior(Human e);
	
	/**
	 * 说明：求元素后继<p>
	 * 条件：<p>
	 * 结果：<p>
	 */
	Human next(Human e);

	/**
	 * 说明：遍历表<p>
	 * 条件：<p>
	 * 结果：<p>
	 */
	void travel(TravelVisitor visitor);

}
