package org.danny.demo.spring1.a03.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class A0020_环绕Interceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("环绕" + arg0.getMethod().getName() + "前拦截");
		Object obj = arg0.proceed();
		System.out.println("环绕" + arg0.getMethod().getName() + "后拦截");
		return obj;
	}


}
