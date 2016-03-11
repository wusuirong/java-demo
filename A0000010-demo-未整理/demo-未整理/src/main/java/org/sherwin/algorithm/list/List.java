package org.sherwin.algorithm.list;

public interface List {
	
	//**********************�����Բ���**********************

	/**
	 * ˵������ձ�<p>
	 * ������<p>
	 * �������Ϊ0<p>
	 */
	void clear();
	
	/**
	 * ˵�������п�<p>
	 * ������<p>
	 * �������Ϊ�շ���true��Ϊ�շ���false<p>
	 */
	boolean isEmpty();
	
	/**
	 * ˵�������<p>
	 * ������<p>
	 * ���������Ԫ�ظ���<p>
	 */
	int length();
	
	/**
	 * ˵�������ռ��С<p>
	 * ������<p>
	 * ��������ر��С<p>
	 */
	int size();

	//**********************Ԫ�ز���**********************

	/**
	 * ˵�����ڵ�i��λ��ǰ����Ԫ��<p>
	 * ������1<=i<=length()+1<p>
	 * ���������Ԫ�غ�length+1<p>
	 */
	void insert(int i, Human e) throws IndexOutOfBoundsException;
	
	/**
	 * ˵����ɾ����i��Ԫ��<p>
	 * ������1<=i<=length()<p>
	 * �����ɾ��Ԫ�غ�length-1<p>
	 */
	Human delete(int i) throws IndexOutOfBoundsException;
	
	//**********************Ԫ�ط���**********************

	/**
	 * ˵����ȡ���е�i��Ԫ��<p>
	 * ������1<=i<=length()<p>
	 * �����<p>
	 */
	Human get(int i) throws IndexOutOfBoundsException;
	
	/**
	 * ˵������Ԫ��λ��<p>
	 * ������<p>
	 * �����<p>
	 */
	int locate(Human e);
	
	/**
	 * ˵������Ԫ��ǰ��<p>
	 * ������<p>
	 * �����<p>
	 */
	Human prior(Human e);
	
	/**
	 * ˵������Ԫ�غ��<p>
	 * ������<p>
	 * �����<p>
	 */
	Human next(Human e);

	/**
	 * ˵����������<p>
	 * ������<p>
	 * �����<p>
	 */
	void travel(TravelVisitor visitor);

}
