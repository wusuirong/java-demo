package com.sherwin.examples.basics;

public class Monster extends Role {

	void attack(Role enemy) {
		System.out.println("用爪攻击" + enemy.name);
	}

}
