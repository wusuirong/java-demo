package com.sherwin.examples.basics;

class Array {
	public static void main(String args[]) {
		int month_days[]; // 声明一个整型数组
		month_days = new int[12]; // 为数组分配空间
		month_days[0] = 31; //初始化元素
		month_days[1] = 28;
		month_days[2] = 31;
		month_days[3] = 30;
		month_days[4] = 31;
		month_days[5] = 30;
		month_days[6] = 31;
		month_days[7] = 31;
		month_days[8] = 30;
		month_days[9] = 31;
		month_days[10] = 30;
		month_days[11] = 31;
		System.out.println("April has " + month_days[3] + " days.");

		int month_days2[] = new int[12]; // 一般写法，声明和分配空间都在一起

		int month_days3[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; //声明，分配空间，初始化都在一起
		System.out.println("April has " + month_days3[3] + " days.");
	}
}
