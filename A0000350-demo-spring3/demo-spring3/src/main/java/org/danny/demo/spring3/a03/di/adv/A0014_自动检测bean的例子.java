package org.danny.demo.spring3.a03.di.adv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


public class A0014_自动检测bean的例子 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a03-di-adv.xml");
		A0014_Bean1 a0014_Bean1 = (A0014_Bean1)ctx.getBean("a0014_Bean1");
		a0014_Bean1.serve();
		A0014_Bean2 a0014_Bean2 = (A0014_Bean2)ctx.getBean("a0014_Bean2");
		a0014_Bean2.work();
	}


}

@Service
class A0014_Bean1 {
	public void serve() {
		System.out.println("bean1 serve");
	}
}

@Service
class A0014_Bean2 {
	public void work() {
		System.out.println("bean2 work");
	}
}