package net.other;

public class VolatileObjectTest {
	/**
	 * 成员变量boolValue使用volatile和不使用volatile会有明显区别的。 本程序需要多试几次，就能知道两者之间的区别的。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final VolatileObjectTest volObj = new VolatileObjectTest();
		Thread t1 = new Thread() {
			public void run() {
				System.out.println("t1 start");
				for (;;) {
					volObj.waitToExit();
				}
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				System.out.println("t2 start");
				for (;;) {
					volObj.swap();
				}
			}
		};
		t2.start();
	}

	boolean boolValue;// 加上volatile 修饰的是时候，程序会很快退出，因为volatile
						// 保证各个线程工作内存的变量值和主存一致。所以boolValue == !boolValue就成为了可能。

	public void waitToExit() {
		if (boolValue == !boolValue)// 非原子操作，因为翻译为机器语言时包括：取boolValue到寄存器r1，取boolValue到r2，r2求反，判断r1 == r2结果存入r3
			System.exit(0);//理论上应该很快会被打断。实际不是，因为此时的boolValue在线程自己内部的工作内存的拷贝，因为它不会强制和主存区域同步，线程2修改了boolValue很少有机会传递到线程一的工作内存中。所以造成了假的“原子现象”。
	}

	public void swap() {// 不断反复修改boolValue，以期打断线程t1.
		boolValue = !boolValue;
	}
}
