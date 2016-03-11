package org.danny.demo.designpattern.strategy.good.ability;

import org.danny.demo.designpattern.strategy.good.Role;

public interface AttackBehavior {
	
	void attack(Role me, Role enemy);

}
