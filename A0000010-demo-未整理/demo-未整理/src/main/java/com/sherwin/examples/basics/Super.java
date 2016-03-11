package com.sherwin.examples.basics;

public class Super {
	
	public static void main(String[] args) {
		LeaderSon son = new LeaderSon(2000);
		System.out.println("通过领导途径了解的收入：" + son.getIncome());
		System.out.println("通过领导儿子途径了解的收入：" + son.getFatherIncome());
	}
}

class Leader {
	private int income; //领导收入，当然不能公开
	
	Leader(int income) {
		this.income = income * 1000; //我实际收入是表面收入1000倍，连儿子都不会知道啊，哈哈哈
	}
	
	int getIncome() {
		return income / 1000; //对外当然要说我工资低啊
	}
}

class LeaderSon extends Leader {
	
	LeaderSon(int income) {
		//调用父类的构造函数，因为子类也无法知道父类的真正收入
		super(income); //我也只知道老爸就这个收入水平
	}
	
	int getFatherIncome() {
		int fatherIncome = super.getIncome(); //调用父类的方法，这里子类和父类没有方法重名，不加super也可以
		return fatherIncome - 1000; //为了低调，把老爸的收入说低点
	}
}