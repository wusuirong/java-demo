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
public class Synchronized作用在方法块上使用的是对象锁 {
	
	public static void main(String[] args) {
		同步类2 handle = new 同步类2();
		SyncThread3 t1 = new SyncThread3(handle);
		SyncThread4 t2 = new SyncThread4(handle);
		
		t1.start();
		t2.start();
	}
}



class 同步类2 {
	
	void work1() {
		int i=0;
		// 这个同步块运行时，work2同步块是不能进入的，但如果把this换掉则另说
		synchronized(this) {
			while (i++<50) {
				System.out.println("work1");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	
	synchronized void work2() {
		int i=0;
		// 这个同步块运行时，work1同步块是不能进入的，但如果把this换掉则另说
		synchronized(this) {
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
}

class SyncThread3 extends Thread {
	
	同步类2 handle;
	
	public SyncThread3(同步类2 handle) {
		this.handle = handle;
	}
	
	@Override
	public void run() {
		handle.work1();
	}
}

class SyncThread4 extends Thread {
	
	同步类2 handle;
	
	public SyncThread4(同步类2 handle) {
		this.handle = handle;
	}
	
	@Override
	public void run() {
		handle.work2();
	}
}