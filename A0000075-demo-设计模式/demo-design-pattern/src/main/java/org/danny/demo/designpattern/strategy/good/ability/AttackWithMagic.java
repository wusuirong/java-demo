package org.danny.demo.designpattern.strategy.good.ability;

import org.danny.demo.designpattern.strategy.good.Role;

public class AttackWithMagic implements AttackBehavior {

	public void attack(Role me, Role enemy) {
		System.out.println(me.getName() + "用魔法攻击" + enemy.getName());
	}

}
