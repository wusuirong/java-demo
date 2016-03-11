package org.sherwin.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class BubbleSort extends Sort {

	public BubbleSort(int size) {
		super(size);
	}

	public void sort() {
		for(int i=data.length; i>0; i--) {
			boolean swaped = false;
			for(int j=0; j<i-1; j++) {
				if(compare(data[j].key,data[j+1].key)) {
					swap(data,j,j+1);
					swaped = true;
				}
			}
			this.printArray("第" + (data.length - i + 1) + "轮排序后");
			if (!swaped) {
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print("输入数组大小：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		BubbleSort bubbleSort = new BubbleSort(size);
		bubbleSort.printArray("初始数组");
		bubbleSort.sort();
		
		bubbleSort.printArray("最终结果");
		bubbleSort.printStatistic();
	}

}
