package org.danny.demo.designpattern.strategy.bad;

/**
 * 剑士
 * @author suirongw
 *
 */
public class Swordman extends Role {

	public Swordman(String name, int hp, int ap) {
		super(name, hp, ap);
	}
	
	/* 
	 * 需要覆盖父类的方法
	 * @see org.sherwin.designpattern.strategy.bad.typea.Role#fly()
	 */
	public void fly() {
		System.out.println(this.name + "不会飞");
	}

}
