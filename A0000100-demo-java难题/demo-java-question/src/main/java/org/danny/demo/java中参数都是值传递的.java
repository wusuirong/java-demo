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
		
		System.out.println("总结：java里方法对基本参数和对象都是值传递的，对象参数是传递对象的地址值");
	}
	
	public static void 方法里修改对象的值(StringBuffer strBuf) {
		strBuf.append(" World");
	}

	public static void 方法里给对象参数重新new一个对象(StringBuffer strBuf) {
		strBuf = new StringBuffer("你好 ");
		strBuf.append("世界!");
	}
}
