package com.sherwin.examples.basics;


public class Transformer {
	public static void main(String[] args) {
		BoPaiTransformer bopai = new BoPaiTransformer();		
		KuangPaiTransformer kuangpai = new KuangPaiTransformer();
		
		bopai.walk();
		bopai.attack();
		bopai.transform();
		bopai.drive();
		
		kuangpai.walk();
		kuangpai.attack();
		kuangpai.transform();
		kuangpai.fly();
	}
}

//博派
class BoPaiTransformer extends Robot implements Transformable, Drivable {

	public void transform() {
		System.out.println("变形为汽车");
	}

	public void drive() {
		System.out.println("开动");
	}
}

//狂派
class KuangPaiTransformer extends Robot implements Transformable, Flyable {

	public void transform() {
		System.out.println("变形为飞机");
	}

	public void fly() {
		System.out.println("飞行");
	}
}

//机器人
class Robot {
	
	void walk() {
		System.out.println("行走");
	}
	
	void attack() {
		System.out.println("攻击");
	}
	
}

//能变形
interface Transformable {
	void transform();
}

//会当飞机开
interface Flyable {
	void fly();
}

//能当车开
interface Drivable {
	void drive();
}

//能当船用
interface Swimmable {
	void swim();
}