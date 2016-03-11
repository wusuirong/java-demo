package org.sherwin.algorithm.queue;

import org.sherwin.algorithm.list.Human;
import org.sherwin.algorithm.list.TravelVisitor;

public interface Queue {

	/**
	 * ˵������ն���<p>
	 * ������<p>
	 * ��������г�Ϊ0<p>
	 */
	void clear();
	
	/**
	 * ˵���������п�<p>
	 * ������<p>
	 * ��������в�Ϊ�շ���true��Ϊ�շ���false<p>
	 */
	boolean isEmpty();
	
	/**
	 * ˵��������г�<p>
	 * ������<p>
	 * ���������Ԫ�ظ���<p>
	 */
	int length();
	
	/**
	 * ˵���������<p>
	 * ������<p>
	 */
	void enQueue(Human e);
	
	/**
	 * ˵����������<p>
	 * ������<p>
	 */
	Human deQueue();
	
	/**
	 * ˵����ȡ��ͷԪ��<p>
	 * ������<p>
	 */
	Human getHead();

	/**
	 * ˵������������<p>
	 * ������<p>
	 * �����<p>
	 */
	void travel(TravelVisitor visitor);
}