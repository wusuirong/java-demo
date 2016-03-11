package org.danny.demo.designpattern.strategy.good.ability;

import org.danny.demo.designpattern.strategy.good.Role;

public class AttackWithSword implements AttackBehavior {

	public void attack(Role me, Role enemy) {
		System.out.println(me.getName() + "用剑攻击" + enemy.getName());
	}

}
