package org.danny.demo.spring1.a03.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class A0020_异常Advice implements ThrowsAdvice {

	public void afterThrowing(Throwable throwable) {
		System.out.println("捕获异常" + throwable);
	}
	
	public void afterThrowing(A0020_自定义异常 exception) {
		System.out.println("捕获自定义异常" + exception);
	}
	
	public void afterThrowing(Method method, Object[] args, Object target, Throwable throwable) {
		System.out.println("执行方法" + method.getName() + "捕获异常时处理参数" + args[0]);
	}
}
