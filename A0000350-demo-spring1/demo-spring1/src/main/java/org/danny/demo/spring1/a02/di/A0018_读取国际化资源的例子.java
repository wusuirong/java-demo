package org.danny.demo.spring1.a02.di;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class A0018_读取国际化资源的例子 implements ApplicationContextAware {

	ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
	
	public void serve() {
		Locale localeCn = new Locale("zh_CN");
		String text = ctx.getMessage("hello", null, localeCn);
		System.out.println("国际化例子，中文: " + text);
		
		Locale localeUs = new Locale("en_US");
		text = ctx.getMessage("hello", null, localeUs);
		System.out.println("国际化例子，英文: " + text);
	}
	
	public static void main(String[] args) {
		// 配置一个id=messageSource的bean
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
		A0018_读取国际化资源的例子 a0018_读取国际化资源的例子 = (A0018_读取国际化资源的例子)ctx.getBean("a0018_读取国际化资源的例子");
		a0018_读取国际化资源的例子.serve();
	}
}
