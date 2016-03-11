package com.sherwin.examples.basics.china;

//通过import引入要使用的类
import com.sherwin.examples.basics.china.guangdong.guangzhou.Human;

public class HumanTester {

	public static void main(String[] args) {
		Human h1 = new Human();//这里默认就使用通过import来的类
		h1.talk();
		
		//如果在代码中还要用到别的Human类，就要写好全路径
		com.sherwin.examples.basics.china.fujian.fuzhou.Human h2 = new com.sherwin.examples.basics.china.fujian.fuzhou.Human();
		h2.talk();
	}
}
