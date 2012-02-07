package com.netblizzard.algorithm;

public class PopSort2 {
	/**
	 * 冒泡排序，从大到小
	 * @param data
	 * @return
	 */
	public static int[] sort(int[] data) {
		if (data == null || data.length <= 1) {
			return data;
		}
		int tmp = 0;
		int size = data.length;
		for (int i = 1; i < size; i++) {
			for (int j = 0; j < size - i; j++) {
				if (data[j] < data[j + 1]) {
					tmp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = tmp;
				}

			}
			System.out.println("第 " + i + "次排序后结果:");
			for (int k = 0; k < data.length; k++) {
				System.out.print(data[k] + "\t");
			}
			System.out.println("");
		}
		return data;
	}

	public static void main(String[] args) {
		int[] data = new int[] { 51, 32, 43, 1, 5, 3, 71, 9, 11 };
		data = PopSort2.sort(data);
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
	}
}
