package org.danny.demo.spring1.a03.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A0020_aop简单例子 implements G0009_aop接口定义 {

	public static void main(String[] args) throws Throwable {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a03-aop-base.xml");
		
		System.out.println("简单的aop例子演示了给bean直接加advice后，所有调用都会拦截，因为没有pointcut指定拦截的条件");
		G0009_aop接口定义 bean = (G0009_aop接口定义)ctx.getBean("a0020_aop简单例子");
		bean.act();
		
		bean.act2();
		
		try {
			bean.actHasException("runtime");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bean.actHasException("checked");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bean.actHasException("define");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			bean.actHasException("throw");
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public void act() {
		System.out.println("A0020_aop的target 执行任务");
	}
	
	@Override
	public void act2() {
		System.out.println("A0020_aop的target 执行任务2");
	}

	@Override
	public void actHasException(String str) throws Throwable {
		switch (str) {
		case "runtime":
			throw new RuntimeException("运行时异常");
		case "checked":
			throw new Exception("检查型异常");
		case "define":
			throw new A0020_自定义异常();
		default:
			throw new Throwable("能扔的");
		}
	}

	
}
