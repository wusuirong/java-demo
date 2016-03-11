package net.other.object;

import java.io.IOException;

public class Caculator {
	
	public static void main(String[] args) throws Exception {
		int num1 = System.in.read();
		if (48 > num1 || 57 < num1) {
			System.out.println("请输入一位数字");
			System.exit(1);
		}
		num1 -= 48;
		char op = (char)System.in.read();
		int num2 = System.in.read();
		if (48 > num2 || 57 < num2) {
			System.out.println("请输入一位数字");
			System.exit(1);
		}
		num2 -= 48;
		
		switch (op) {
		case '+':
			System.out.println(num1 + num2);
			break;
		case '-':
			System.out.println(num1 - num2);
			break;
		case '*':
			System.out.println(num1 * num2);
			break;
		case '/':
			if (0 != num2) {
				System.out.println(num1 / num2);
			} else {
				System.out.println("除数不能为0");
			}			
			break;
		}
	}

}
