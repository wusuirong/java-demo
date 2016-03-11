package org.danny.demo.designpattern.strategy.good.ability;

import org.danny.demo.designpattern.strategy.good.Role;

public class FlyWithMagic implements FlyBehavior {

	public void fly(Role me) {
		System.out.println(me.getName() + "使用魔法飞行");
	}

}
