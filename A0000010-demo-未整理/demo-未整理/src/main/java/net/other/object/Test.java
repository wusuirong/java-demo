package net.other.object;

public class Test {
	
	public int a;
	
	String s;
	
	static int x;
	
	public Test(int a, int b) {
		System.out.println(a + b);
	}
	
	public void test(int c) {
		final int z = 1;
		class innerClass2 {
			public int a;
			
			{
				System.out.println("hello" + z);
				a = 2;
			}
			
			public int getA() {
				return a;
			}
			
			public int getOuterA() {
				return Test.this.a;
			}
		}
	}
	
	public class innerClass1 {
		public int a;
		
		int m = 2;
		
		{
			System.out.println("hello");
			a = 2;
			x = 1;
		}
		
		public int getA() {
			return a;
		}
		
//		public int getOuterA() {
//			return Test.this.a;
//		}
	}
}
