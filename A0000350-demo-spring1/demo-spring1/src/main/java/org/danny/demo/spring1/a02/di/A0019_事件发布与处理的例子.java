package org.danny.demo.spring1.a02.di;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class A0019_事件发布与处理的例子 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
		A0019_事件发布者 a0019_事件发布者 = (A0019_事件发布者)ctx.getBean("a0019_事件发布者");
		a0019_事件发布者.send("事件标记");
	}
	
}

class MyEvent extends ApplicationEvent {
	
	String tag;

	public MyEvent(Object source, String tag) {
		super(source);
		this.tag = tag;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2107042478369433467L;
	
}

class A0019_事件发布者 implements ApplicationContextAware {
	
	ApplicationContext ctx;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
	
	public void send(String msg) {
		ctx.publishEvent(new MyEvent(this, msg));
	}

}

class A0019_事件监听者 implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof MyEvent) {
			MyEvent e = (MyEvent)event;
			System.out.println("收到事件: " + e.tag);
		}
	}
	
}