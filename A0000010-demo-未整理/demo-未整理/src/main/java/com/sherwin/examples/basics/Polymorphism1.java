package com.sherwin.examples.basics;

import java.util.Date;
import java.util.Random;

public class Polymorphism1 {

	public static void main(String[] args) {
		testHuman();
		testMonster();
	}
	
	public static void testHuman() {
		Human hero = new Human();
		Monster enemy = new Monster();
		enemy.name = "敌人";
		hero.attack(enemy);
	}
	
	public static void testMonster() {
		Monster hero = new Monster();
		Monster enemy = new Monster();
		enemy.name = "敌人";
		hero.attack(enemy);
	}
}
