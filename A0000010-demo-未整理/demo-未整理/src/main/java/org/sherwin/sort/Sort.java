package org.sherwin.sort;

import java.util.Random;

public class Sort {
	
	static int compare_time = 0;
	
	static int swap_time = 0;

	int size = 10;
	
	int ceil = 100;
	
	Item data[] = null;
	
	public Sort(int size) {
		this.size = size;
		data = new Item[size];
		init();
	}
	
	public void init() {
		Random random = new Random(System.currentTimeMillis());
		for(int i=0; i<size; i++) {
			data[i] = new Item();
			data[i].key = random.nextInt(ceil);
		}
	}
	
	public void printArray(String hint) {
		System.out.println(hint);
		for(int i=0; i<size; i++) {
			System.out.print(data[i].key + ", ");
		}
		System.out.println();
	}
	
	public void printStatistic() {
		System.out.println("比较次数：" + compare_time);
		System.out.println("交换次数：" + swap_time);
	}
	
	public boolean compare(int a, int b) {
		compare_time++;
		return (a > b);
	}
	
	public void swap(Item[] array, int i, int j) {
		Item tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
		swap_time++;
	}
}
