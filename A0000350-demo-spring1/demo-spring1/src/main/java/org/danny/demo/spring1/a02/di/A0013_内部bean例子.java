package org.danny.demo.spring1.a02.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class A0013_内部bean例子 implements G0009_玩家接口定义 {
	

	A0013_InnerClazz innerClazz;
	
	@Override
	public void act() {
		innerClazz.act();
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
		A0013_内部bean例子 a0013_内部bean例子 = (A0013_内部bean例子)ctx.getBean("a0013_内部bean例子");
		a0013_内部bean例子.act();
	}

	public void setInnerClazz(A0013_InnerClazz innerClazz) {
		this.innerClazz = innerClazz;
	}

}

class A0013_InnerClazz {
	public void act() {
		System.out.println("内部动作");
	}
}