package org.danny.demo.designpattern.strategy.good.ability;

import org.danny.demo.designpattern.strategy.good.Role;

public class FlyWithWings implements FlyBehavior {

	public void fly(Role me) {
		System.out.println(me.getName() + "使用翅膀飞行");
	}

}
