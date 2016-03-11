package com.sherwin.examples.basics;

public class Human extends Role {
	
	int magicPower;//MP 魔法力
	
	void talk(Human human) {
		System.out.println("你好，" + human.name);
	}

	void attack(Role enemy) {
		System.out.println("用拳头攻击" + enemy.name);
	}
}
