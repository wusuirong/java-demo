package com.sherwin.examples.basics;

public class RoleRunner {
	
	public static void main(String[] args) {
		Role hero = new Role();
		
		hero.roleId = 1;
		hero.name = "勇者";
		hero.healthPower = 100;
		hero.attackPower = 10;
		hero.defence = 5;
		hero.hitRate = 30;
		
		Role enemy = new Role();
		
		enemy.roleId = 2;
		enemy.name = "强盗";
		enemy.healthPower = 30;
		enemy.attackPower = 3;
		enemy.defence = 5;
		enemy.hitRate = 20;
		
		hero.attack(enemy);
	}
}