package org.sherwin.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class SelectionSort extends Sort {

	public SelectionSort(int size) {
		super(size);
	}

	public void sort() {
		for(int i=0; i<data.length; i++) {
			int minKey = data[i].key;
			int number = i;
			for(int j=i; j<data.length; j++) {
				if(compare(minKey, data[j].key)) {
					minKey = data[j].key;
					number = j;
				}
			}
			
			if (i != number) {
				swap(data, i, number);
			}			
			
			this.printArray("��" + (i+1) + "�������");
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print("���������С��");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		SelectionSort bubbleSort = new SelectionSort(size);
		bubbleSort.printArray("��ʼ����");
		bubbleSort.sort();
		
		bubbleSort.printArray("���ս��");
		bubbleSort.printStatistic();
	}

}
