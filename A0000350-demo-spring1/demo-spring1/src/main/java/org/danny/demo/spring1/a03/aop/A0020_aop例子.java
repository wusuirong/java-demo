package org.danny.demo.spring1.a03.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A0020_aop例子 implements G0009_aop接口定义 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a03-aop.xml");
		G0009_aop接口定义 bean = (G0009_aop接口定义)ctx.getBean("a0020_aop例子");
		bean.act();
		
		try {
			bean.actHasException(-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bean.actHasException(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bean.actHasException(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bean.actHasException(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void act() {
		System.out.println("A0020_aop的target 执行任务");
	}

	@Override
	public void actHasException(int i) throws Exception {
		switch (i) {
		case -1:
			throw new RuntimeException("运行时异常");
		case 0:
			throw new Exception("检查型异常");
		case 1:
			throw new A0020_自定义异常();
		default:
			throw new Exception("检查型异常");
		}
	}
}
