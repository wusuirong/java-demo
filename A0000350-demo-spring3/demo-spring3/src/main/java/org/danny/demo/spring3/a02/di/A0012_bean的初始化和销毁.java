package org.danny.demo.spring3.a02.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class A0012_bean的初始化和销毁 implements G0009_玩家接口定义 {
	

	/**
	 * 可以在bean定义里指定init-method<p>
	 * 也可以在xml文件头给所有bean指定default-init-method
	 */
	public void init() {
		System.out.println("初始化");
	}

	public void destroy() {
		System.out.println("销毁");
	}
	
	@Override
	public void act() {
		System.out.println("勇者出发了");
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("a02-di-applicationContext.xml");
		A0012_bean的初始化和销毁 a0012_bean的初始化和销毁 = (A0012_bean的初始化和销毁)ctx.getBean("a0012_bean的初始化和销毁");
		a0012_bean的初始化和销毁.act();
		
		// 显式订阅一个jvm关闭的消息通知，否则context无法destroy bean，或者手工调用ctx.close();
		ctx.registerShutdownHook();
	}

}
