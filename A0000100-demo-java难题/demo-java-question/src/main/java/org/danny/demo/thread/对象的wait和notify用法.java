package org.danny.demo.thread;

/**
 * 
 * @author wusuirong
 *
 * @Description 三线程通过同步的方式，同时运行，轮流工作的例子
 *
 * @Email wusuirong@xxx.com
 *
 *        www.xxx.com Copyright (c) 2014 All Rights Reserved.
 */
public class 对象的wait和notify用法 {

	public static void main(String[] args) throws Exception {
		String a = "锁a";
		String b = "锁b";
		String c = "锁c";
		SyncWorker pa = new SyncWorker("A", c, a);
		SyncWorker pb = new SyncWorker("B", a, b);
		SyncWorker pc = new SyncWorker("C", b, c);

		new Thread(pa).start();
		Thread.sleep(100); // 确保按顺序A、B、C执行
		new Thread(pb).start();
		Thread.sleep(100);
		new Thread(pc).start();
		Thread.sleep(100);
	}

}

class SyncWorker implements Runnable {

	private String name;
	private Object prev;
	private Object self;

	public SyncWorker(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				System.out.println(name + "获取" + prev);
				synchronized (self) {
					System.out.println(name + "获取" + self);
					System.out.println(name + "工作 ");
					count--;

					self.notify();
					System.out.println(name + "已经notify" + self + "的等待者，马上离开" + self + "同步块");
				}

				try {
					System.out.println(name + "释放" + prev + "锁，进入休眠态");
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}
}