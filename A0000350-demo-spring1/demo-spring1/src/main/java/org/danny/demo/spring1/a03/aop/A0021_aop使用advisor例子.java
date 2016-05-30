package org.danny.demo.spring1.a03.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A0021_aop使用advisor例子 implements G0009_aop接口定义 {

	public static void main(String[] args) throws Throwable {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a03-aop-adv.xml");
		
		System.out.println("使用advisor后，advisor是advice+pointcut的结合体，proxy就可以按pointcut的要求调用advice，而不是不管三七二十一都拦截");
		G0009_aop接口定义 bean = (G0009_aop接口定义)ctx.getBean("a0020_aop使用advisor例子");
		bean.act();
		
		bean.act2();
		
		bean.act();

	}

	@Override
	public void act() {
		System.out.println("A0020_aop的target 执行任务");
	}

	@Override
	public void actHasException(String str) throws Throwable {
		System.out.println("准备抛异常");
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

	@Override
	public void act2() {
		System.out.println("A0020_aop的target 执行任务2");
	}
}
