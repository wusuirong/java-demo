package org.danny.demo.designpattern.strategy.good;

import org.danny.demo.designpattern.strategy.good.ability.AttackBehavior;
import org.danny.demo.designpattern.strategy.good.ability.AttackWithMagic;
import org.danny.demo.designpattern.strategy.good.ability.AttackWithSword;
import org.danny.demo.designpattern.strategy.good.ability.FlyBehavior;
import org.danny.demo.designpattern.strategy.good.ability.FlyNoWay;
import org.danny.demo.designpattern.strategy.good.ability.FlyWithMagic;
import org.danny.demo.designpattern.strategy.good.ability.FlyWithWings;

public class Test {
	
	public static void main(String[] args) {
		AttackBehavior attackWithMagic = new AttackWithMagic();
		AttackBehavior attackWithSword = new AttackWithSword();
		
		FlyBehavior flyNoWay = new FlyNoWay();
		FlyBehavior flyWithMagic = new FlyWithMagic();
		FlyBehavior flyWithWings = new FlyWithWings();
		
		Magician magician = new Magician("魔法师", 10, 10);
		magician.setAttackBehavior(attackWithMagic);
		magician.setFlyBehavior(flyWithMagic);
		
		Swordman swordman = new Swordman("剑士", 20, 20);
		swordman.setAttackBehavior(attackWithSword);
		swordman.setFlyBehavior(flyNoWay);
		
		Orc orc = new Orc("兽人", 30, 15);
		orc.setAttackBehavior(attackWithSword);
		orc.setFlyBehavior(flyWithWings);
		
		magician.attack(orc);
		magician.fly();
		
		swordman.attack(magician);
		swordman.fly();
		
		orc.attack(swordman);
		orc.fly();
	}

}
