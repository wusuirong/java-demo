package org.danny.demo.spring1.a02.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class A0012_bean的初始化和销毁 implements G0009_玩家接口定义 {
	
	public void init() {
		System.out.println("A0012_bean的初始化和销毁 初始化");
	}

	public void destroy() {
		System.out.println("A0012_bean的初始化和销毁 销毁");
	}
	
	@Override
	public void act() {
		System.out.println("A0012_bean的初始化和销毁 勇者出发了");
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
		A0012_bean的初始化和销毁 a0012_bean的初始化和销毁 = (A0012_bean的初始化和销毁)ctx.getBean("a0012_bean的初始化和销毁");
		a0012_bean的初始化和销毁.act();
		
		// 显式订阅一个jvm关闭的消息通知，否则context无法destroy bean，或者手工调用ctx.close();
		ctx.close();
	}

}
