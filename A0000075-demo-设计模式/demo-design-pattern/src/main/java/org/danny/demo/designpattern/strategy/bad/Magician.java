package org.danny.demo.designpattern.strategy.bad;

/**
 * 魔法师
 * @author suirongw
 *
 */
public class Magician extends Role {

	public Magician(String name, int hp, int ap) {
		super(name, hp, ap);
	}
	
	/* 
	 * 需要覆盖父类的方法
	 * @see org.sherwin.designpattern.strategy.bad.typea.Role#fly()
	 */
	public void fly() {
		System.out.println(this.name + "使用魔法飞行");
	}

}
