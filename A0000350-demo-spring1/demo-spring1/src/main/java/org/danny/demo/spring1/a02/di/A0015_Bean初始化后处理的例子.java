package org.danny.demo.spring1.a02.di;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring的context载入bean声明后会调用BeanPostProcessor
 * 比如
 * ApplicationContextAwareProcessor
 */
public class A0015_Bean初始化后处理的例子 implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("bean初始化完成前的post process");
		System.out.println("beanName: " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("bean初始化完成后的post process");
		System.out.println("beanName: " + beanName);
		return bean;
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
	}

	
}
