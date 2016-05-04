package org.danny.demo.spring1.a02.di;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring的factory载入所有bean定义后，实例化bean前调用BeanFactoryPostProcessor
 * 比如
 * PropertyPlaceholderConfigurer
 * CustomEditorConfigurer
 */
public class A0016_BeanFactory初始化后处理的例子 implements BeanFactoryPostProcessor {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("bean factory初始化后的处理");
	}

	
}
