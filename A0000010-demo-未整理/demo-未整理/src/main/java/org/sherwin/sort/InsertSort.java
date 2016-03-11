package org.sherwin.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class InsertSort extends Sort {

	public InsertSort(int size) {
		super(size);
	}

	public void sort() {
		for(int i=0; i<data.length; i++) {
			Item tmp = data[i];
			for(int j=i-1; j>=0; j--) {
				if (compare(data[j].key, tmp.key) && j>0) {
					data[j+1] = data[j];
					swap_time++;
				} else {
					data[j+1] = tmp;
					tmp = null;
					swap_time++;
				}
			}
			if (null != tmp) {
				data[0] = tmp;
				tmp = null;
			}
			this.printArray("第" + (i+1) + "轮排序后");
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print("输入数组大小：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		InsertSort bubbleSort = new InsertSort(size);
		bubbleSort.printArray("初始数组");
		bubbleSort.sort();
		
		bubbleSort.printArray("最终结果");
		bubbleSort.printStatistic();
	}

}
