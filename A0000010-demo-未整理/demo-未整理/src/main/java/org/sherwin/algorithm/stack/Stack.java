package org.sherwin.algorithm.stack;

import org.sherwin.algorithm.list.Human;
import org.sherwin.algorithm.list.TravelVisitor;

public interface Stack {

	/**
	 * ˵�������ջ<p>
	 * ������<p>
	 * �����ջ��Ϊ0<p>
	 */
	void clear();
	
	/**
	 * ˵����ջ�п�<p>
	 * ������<p>
	 * �����ջ��Ϊ�շ���true��Ϊ�շ���false<p>
	 */
	boolean isEmpty();
	
	/**
	 * ˵������ջ��<p>
	 * ������<p>
	 * ���������Ԫ�ظ���<p>
	 */
	int length();
	
	/**
	 * ˵������ջ<p>
	 * ������<p>
	 */
	void push(Human e);
	
	/**
	 * ˵������ջ<p>
	 * ������<p>
	 */
	Human pop();
	
	/**
	 * ˵����ȡջ��Ԫ��<p>
	 * ������<p>
	 */
	Human getTop();

	/**
	 * ˵��������ջ<p>
	 * ������<p>
	 * �����<p>
	 */
	void travel(TravelVisitor visitor);
}