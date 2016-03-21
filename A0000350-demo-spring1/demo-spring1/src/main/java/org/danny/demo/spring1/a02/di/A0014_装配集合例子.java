package org.danny.demo.spring1.a02.di;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class A0014_装配集合例子 implements G0009_玩家接口定义 {

	int[] intArray;
	
	List<G0009_玩家接口定义> objectList;
	
	Set<String> strSet;
	
	Map<String, String> strMap;
	
	Properties props;
	
	@Override
	public void act() {
		for (int i : intArray) {
			System.out.println("数组成员: " + i);
		}
		
		for (G0009_玩家接口定义 obj : objectList) {
			obj.act();
		}
		
		for (String str : strSet) {
			System.out.println(str);
		}
		
		for (Entry<String, String> entry : strMap.entrySet()) {
			System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
		}
		
		System.out.println("value1InProp: " + props.getProperty("key1InProp"));
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
		A0014_装配集合例子 a0014_装配集合例子 = (A0014_装配集合例子)ctx.getBean("a0014_装配集合例子");
		a0014_装配集合例子.act();
	}

	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}

	public void setStrSet(Set<String> strSet) {
		this.strSet = strSet;
	}

	public void setStrMap(Map<String, String> strMap) {
		this.strMap = strMap;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setObjectList(List<G0009_玩家接口定义> objectList) {
		this.objectList = objectList;
	}



}
