package org.danny.demo.designpattern.strategy.bad;

public abstract class Role {
	
	protected String name;
	protected int hp;
	protected int ap;
	
	/**
	 * 兽人族才有的技能
	 */
	public void fly() {
		System.out.println(this.name + "飞行");
	}

	public Role(String name, int hp, int ap) {
		this.name = name;
		this.hp = hp;
		this.ap = ap;
	}
	
	public void attack(Role enemy) {
		System.out.println(this.name + "攻击" + enemy.getName());
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
}
