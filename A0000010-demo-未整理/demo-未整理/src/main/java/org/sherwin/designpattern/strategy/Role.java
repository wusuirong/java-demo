package org.sherwin.designpattern.strategy;

import org.sherwin.designpattern.strategy.behavior.attack.AttackBehavior;
import org.sherwin.designpattern.strategy.behavior.defend.DefendBehavior;
import org.sherwin.designpattern.strategy.behavior.run.RunBehavior;
import org.sherwin.designpattern.strategy.behavior.usemagic.UseMagicBehavior;

public abstract class Role {
	
	protected int health;
	
	protected int attack;
	
	protected int defence;
	
	protected int speed;
	
	protected int magic;
	
	protected AttackBehavior attackBehavior;
	
	protected DefendBehavior defendBehavior;
	
	protected RunBehavior runBehavior;
	
	protected UseMagicBehavior useMagicBehavior;
	
	/*
	 * ¹¥»÷£¬·ÀÓù£¬ÅÜ²½£¬Ê©·¨
	 */
	public void attack() {
		attackBehavior.attack();
	}
	
	public void defend() {
		defendBehavior.defend();
	}
	
	public void run() {
		runBehavior.run();
	}
	
	public void useMagic() {
		useMagicBehavior.useMagic();
	}

}
