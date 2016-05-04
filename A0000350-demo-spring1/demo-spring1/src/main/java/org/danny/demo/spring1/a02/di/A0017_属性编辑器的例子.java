package org.danny.demo.spring1.a02.di;

import java.beans.PropertyEditorSupport;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 如果目标值是字符串，但提供的源值是数字，可以用属性编辑器做转换
 * xml中配置customEditorConfigurer
 */
public class A0017_属性编辑器的例子 {
	
	private PhoneNumber phoneNumber;

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-a02-di.xml");
		
		A0017_属性编辑器的例子 a0017_属性编辑器的例子 = (A0017_属性编辑器的例子)ctx.getBean("a0017_属性编辑器的例子");
		System.out.println(a0017_属性编辑器的例子.getPhoneNumber().getNumber());
	}
	
}

class PhoneNumber {
	private String areaCode;
	private String prefix;
	private String number;
	
	public PhoneNumber(String areaCode, String prefix, String number) {
		this.areaCode = areaCode;
		this.prefix = prefix;
		this.number = number;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getNumber() {
		return number;
	}
	
}

class Phone属性编辑器 extends PropertyEditorSupport {
	@Override
	public void setAsText(String textValue) {
		String areaCode = textValue.substring(0, 3);
		String prefix = textValue.substring(4, 7);
		String number = textValue.substring(8);
		
		PhoneNumber phone = new PhoneNumber(areaCode, prefix, number);
		setValue(phone);
	}
	
}