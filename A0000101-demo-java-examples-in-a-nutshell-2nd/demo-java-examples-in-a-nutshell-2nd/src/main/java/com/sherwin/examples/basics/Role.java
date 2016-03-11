package com.sherwin.examples.basics;

public class Role {
	
	int roleId;//ID
	
	String name;//名称
	
	int healthPower;//HP 体力
	
	int attackPower;//AP 攻击力
	
	int hitRate;//命中率
	
	int defence;//防御力
	
	void attack(Role enemy) {
		System.out.println("攻击" + enemy.name);
	}
}