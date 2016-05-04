package org.danny.demo.thread;

/**
 *             
 * @author wusuirong
 *
 * @Description 
 * join到某线程是为了等待某线程结束再继续运行
 *
 * @Email wusuirong@xxx.com
 *
 * www.xxx.com
 * Copyright (c) 2014 All Rights Reserved.
 */
public class Join到子线程的用法 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + "主线程运行开始!");
		JoinThread t1 = new JoinThread("A");
		JoinThread t2 = new JoinThread("B");
		t1.start();
		t2.start();
		
		// 调用child thread的join方法时，里面逻辑就是同步等待child thread执行结束，如果注释掉，main thread就会马上结束。一般在需要等待child thread返回结果的情况下使用。
		t1.join();
		t1.join();
		System.out.println(Thread.currentThread().getName() + "主线程运行结束!");

	}
}

class JoinThread extends Thread {
	private String name;

	public JoinThread(String name) {
		super(name);
		this.name = name;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
		for (int i = 0; i < 5; i++) {
			System.out.println("子线程" + name + "运行 : " + i);
			try {
				sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
	}
}