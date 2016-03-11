package com.sherwin.examples.basics;

import java.util.Date;
import java.util.Random;

public class Polymorphism2 {

	public static void main(String[] args) {
		Role role = null;
		
		for (int i=0; i<5; i++) {
			role = startGameAndGiveMeARole();
			testAttack(role);
		}
	}
	
	public static void testAttack(Role role) {
		Monster enemy = new Monster();
		enemy.name = "敌人";
		role.attack(enemy);
	}
	
	/*
	 * 采用随机数方式决定返回Human还是Monster
	 */
	static Role startGameAndGiveMeARole() {
		Random r = new Random();//初始化随机数生成器
		int i = r.nextInt();//随机得到一个整数
		if (i%2 == 0) {
			return new Human();
		} else {
			return new Monster();
		}
	}
}
