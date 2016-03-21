package org.danny.demo.spring1.a02.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class A0010_基于xml注入的bean implements G0009_玩家接口定义 {
	
	/**
	 * 生命值
	 */
	private int health = 10;
	
	private G0009_武器接口定义 weapon;
	
	private int attack;
	
	public A0010_基于xml注入的bean(int health, G0009_武器接口定义 weapon) {
		this.health = health;
		this.weapon = weapon;
	}

	@Override
	public void act() {
		System.out.println("A0010_基于xml注入的bean 勇者出发了，目前生命值=" + health + "，攻击力=" + attack);
		weapon.attack();
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
		A0010_基于xml注入的bean a0010_基于xml注入的bean = (A0010_基于xml注入的bean)ctx.getBean("a0010_基于xml注入的bean");
		a0010_基于xml注入的bean.act();
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

}
