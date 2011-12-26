package com.netblizzard.algorithm;

import java.util.Random;

public class PopSort {
	/**
	 * 冒泡排序，从大到小
	 * @param data
	 * @return
	 */
	public static int[] sort(int[] data) {
		int tmp;
		for (int i = data.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (data[j] < data[j + 1]) {
					tmp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = tmp;
				}
			}

		}
		return data;
	}

	public static void main(String[] args) {
		int length = 30;
		int[] data = new int[length];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < length; i++) {
			data[i] = random.nextInt(1024);
		}
		int[] result = PopSort.sort(data);
		for (int j = 0; j < result.length; j++) {
			System.out.println(j + "\t\t" + result[j]);
		}
	}
}
