package com.netblizzard.algorithm;

public class HalfSearch {

	public static int search(int[] data, int num) {
		if (data == null || data.length <= 0) {
			return -1;
		}
		int begin = 0;
		int end = data.length - 1;
		int index = -1;
		while ((index != begin) && (index != end)) {
			index = (begin + end) / 2;
			if (num == data[index]) {
				return index;
			} else if (num > data[index]) {
				begin = index + 1;
			} else {
				end = index - 1;
			}
			// 未查找到此数据
			if (begin > end) {
				return -1;
			}
		}
		return index;
	}

	public static void main(String args[]) {
		// int[] data = new int[] { 1, 3, 4, 5, 7, 9, 12, 156, 123, 245 };
		// int num = 24;
		// System.out.println(HalfSearch.search(data, num));
		
		
//		int faboLength = 30;
//		int faboIndex = 23;
//		int[] fabonacci = new int[faboLength];
//		fabonacci[0] = 1;
//		fabonacci[1] = 2;
//		for (int i = 2; i < faboLength; i++) {
//			fabonacci[i] = fabonacci[i - 2] + fabonacci[i - 1];
//		}
//		System.out.println(HalfSearch.search(fabonacci, (int)Fabonacci.getByIndex(faboIndex)));
		
		int length = Integer.MAX_VALUE / 8 - 1;
		int[] data3 = new int[length];
		for(int i = 0; i < length; i++) {
			data3[i] = 2 * i + 1;
		}
		System.out.println(HalfSearch.search(data3, 13579));
	}
}
