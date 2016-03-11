package org.danny.demo.designpattern.strategy.good;

import org.danny.demo.designpattern.strategy.good.ability.AttackBehavior;
import org.danny.demo.designpattern.strategy.good.ability.FlyBehavior;


public class Role {

	protected String name;
	protected int hp;
	protected int ap;
	
	protected FlyBehavior flyBehavior;
	protected AttackBehavior attackBehavior;

	public Role(String name, int hp, int ap) {
		this.name = name;
		this.hp = hp;
		this.ap = ap;
	}
	
	public void attack(Role enemy) {
		attackBehavior.attack(this, enemy);
	}

	public void fly() {
		flyBehavior.fly(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAp() {
		return ap;
	}

	public void setAp(int ap) {
		this.ap = ap;
	}

	public FlyBehavior getFlyBehavior() {
		return flyBehavior;
	}

	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}

	public AttackBehavior getAttackBehavior() {
		return attackBehavior;
	}

	public void setAttackBehavior(AttackBehavior attackBehavior) {
		this.attackBehavior = attackBehavior;
	}
}
