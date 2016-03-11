package org.danny.demo.spring3.a02.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class A0013_xml中使用命名空间p装配属性的bean implements G0009_玩家接口定义 {
	
	/**
	 * 生命值
	 */
	private int health = 10;
	
	private G0009_武器接口定义 weapon;
	

	@Override
	public void act() {
		System.out.println("勇者出发了，目前生命值=" + health);
		weapon.attack();
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("a02-di-applicationContext.xml");
		A0013_xml中使用命名空间p装配属性的bean a0013_xml中使用命名空间p装配属性的bean = (A0013_xml中使用命名空间p装配属性的bean)ctx.getBean("a0013_xml中使用命名空间p装配属性的bean");
		a0013_xml中使用命名空间p装配属性的bean.act();
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setWeapon(G0009_武器接口定义 weapon) {
		this.weapon = weapon;
	}

}
