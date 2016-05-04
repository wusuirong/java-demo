package org.danny.demo.thread;

/**
 *             
 * @author wusuirong
 *
 * @Description 
 * yield是放弃这次运行，进入可运行状态，jvm在可运行线程列表里再挑一个运行，所以可能又挑回这个线程。这是和sleep最大的区别。
 *
 * @Email wusuirong@xxx.com
 *
 * www.xxx.com
 * Copyright (c) 2014 All Rights Reserved.
 */
public class 线程的yield的用法 {

	public static void main(String[] args) {
		YieldThread yt1 = new YieldThread("一");
		YieldThread yt2 = new YieldThread("二");
		YieldThread yt3 = new YieldThread("三");
		YieldThread yt4 = new YieldThread("四");
		YieldThread yt5 = new YieldThread("五");
		yt1.start();
		yt2.start();
		yt3.start();
		yt4.start();
		yt5.start();
	}
}

class YieldThread extends Thread {
	public YieldThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			System.out.println("" + this.getName() + "-----" + i);
			// 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
			if (i == 30) {
				Thread.yield();
			}
		}

	}
}