package org.danny.demo.spring3.a02.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A0011_把单例类定义为bean implements G0009_玩家接口定义 {

	private A0011_把单例类定义为bean() {
		
	}
	
	private static class SingletonHolder {
		static A0011_把单例类定义为bean instance = new A0011_把单例类定义为bean();
	}
	
	public static A0011_把单例类定义为bean getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public void act() {
		System.out.println("单例类行动");
	}
	
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("a02-di-applicationContext.xml");
		A0011_把单例类定义为bean a0011_把单例类定义为bean = (A0011_把单例类定义为bean)ctx.getBean("a0011_把单例类定义为bean");
		a0011_把单例类定义为bean.act();
	}


	
}
