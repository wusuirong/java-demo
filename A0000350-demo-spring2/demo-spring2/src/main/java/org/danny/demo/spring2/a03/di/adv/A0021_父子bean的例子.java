package org.danny.demo.spring2.a03.di.adv;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class A0021_父子bean的例子 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a03-di-adv.xml");
		A0021_玩家 danny = (A0021_玩家)ctx.getBean("danny");
		A0021_玩家 joe = (A0021_玩家)ctx.getBean("joe");
		
		System.out.println(danny.name + ", " + danny.weapon);
		System.out.println(joe.name + ", " + joe.weapon);
		
		A0021_门 door = (A0021_门)ctx.getBean("door");
		A0021_墙 wall = (A0021_墙)ctx.getBean("wall");
		System.out.println(door.height);
		System.out.println(wall.height);
	}
}

class A0021_玩家 {
	String name;
	String weapon;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
}

class A0021_门 {
	int height;
	String action;
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}

class A0021_墙 {
	int height;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}