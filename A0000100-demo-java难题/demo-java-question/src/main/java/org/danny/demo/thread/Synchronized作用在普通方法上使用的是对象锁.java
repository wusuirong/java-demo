package org.danny.demo.thread;

/**
 *             
 * @author wusuirong
 *
 * @Description 
 * 在对象的普通方法上使用synchronized的话，当线程进入方法时，它获得的锁是这个对象
 * 在类方法上使用synchronized的话，当线程进入方法时，它获得的锁是这个类
 *
 * @Email wusuirong@xxx.com
 *
 * www.xxx.com
 * Copyright (c) 2014 All Rights Reserved.
 */
public class Synchronized作用在普通方法上使用的是对象锁 {
	
	public static void main(String[] args) {
		同步类 handle = new 同步类();
		SyncThread1 t1 = new SyncThread1(handle);
		SyncThread2 t2 = new SyncThread2(handle);
		
		t1.start();
		t2.start();
	}
}



class 同步类 {
	// 这个方法运行时，work2是不能进入的
	synchronized void work1() {
		int i=0;
		while (i++<50) {
			System.out.println("work1");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 这个方法运行时，work1是不能进入的
	synchronized void work2() {
		int i=0;
		while (i++<50) {
			System.out.println("work2");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SyncThread1 extends Thread {
	
	同步类 handle;
	
	public SyncThread1(同步类 handle) {
		this.handle = handle;
	}
	
	@Override
	public void run() {
		handle.work1();
	}
}

class SyncThread2 extends Thread {
	
	同步类 handle;
	
	public SyncThread2(同步类 handle) {
		this.handle = handle;
	}
	
	@Override
	public void run() {
		handle.work2();
	}
}