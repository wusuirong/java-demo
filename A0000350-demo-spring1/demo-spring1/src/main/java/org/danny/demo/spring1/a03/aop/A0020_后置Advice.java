package org.danny.demo.spring1.a03.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class A0020_后置Advice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("执行方法" + method.getName() + "后拦截");
	}


}
