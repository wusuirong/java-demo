package net.other.object;

public class Outer2 {
	public void outMethod() {
		final int beep = 0;
		class Inner {
			// 使用beep
			public void test() {
				System.out.println("1" + beep);
			}
		}
		Inner in = new Inner();
	}
}
