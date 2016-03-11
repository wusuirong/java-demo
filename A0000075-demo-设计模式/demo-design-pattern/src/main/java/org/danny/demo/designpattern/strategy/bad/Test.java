package org.danny.demo.designpattern.strategy.bad;

public class Test {
	
	public static void main(String[] args) {
		Magician magician = new Magician("魔法师", 10, 10);
		Swordman swordman = new Swordman("剑士", 20, 20);
		Orc orc = new Orc("兽人", 30, 15);
		
		magician.attack(orc);
		magician.fly();
		
		swordman.fly();
		
		orc.attack(swordman);
		orc.fly();
	}

}
