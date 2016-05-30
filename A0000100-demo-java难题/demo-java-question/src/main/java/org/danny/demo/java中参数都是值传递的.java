package org.danny.demo;

public class java中参数都是值传递的 {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("Hello ");
		System.out.println("执行方法前, sb = " + sb);
		方法里修改对象的值(sb);
		System.out.println("方法里修改对象的值, sb = " + sb);
		
		StringBuffer sb2 = new StringBuffer("Hello ");
		System.out.println("执行方法前, sb2 = " + sb2);
		方法里给对象参数重新new一个对象(sb2);
		System.out.println("方法里给对象参数重新new一个对象, sb2 = " + sb2);
		
		System.out.println("总结：java里调用方法时对基本类型和对象都是新建一个方法变量，然后进行值拷贝的，所以方法里改基本类型的值不影响外部的变量，"
						+ "方法里把对象变量指向其他对象引用后修改对象内容不影响外部的变量，"
						+ "只有修改对象的属性的方式才能改变外部变量的内容。");
	}
	
	/**
	 * strBuf变量的值取sb的值，所以strBuf和sb指向同一个内存对象
	 * @param strBuf
	 */
	public static void 方法里修改对象的值(StringBuffer strBuf) {
		strBuf.append(" World");
	}

	/**
	 * 在堆上new一个对象后，把对象引用赋给strBuf后，修改strBuf就不影响sb了
	 * @param strBuf
	 */
	public static void 方法里给对象参数重新new一个对象(StringBuffer strBuf) {
		strBuf = new StringBuffer("你好 ");
		strBuf.append("世界!");
	}
}
