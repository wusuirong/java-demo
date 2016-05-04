package org.danny.demo.spring1.a03.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class A0020_前置Advice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("执行方法" + method.getName() + "前拦截");
	}

}
