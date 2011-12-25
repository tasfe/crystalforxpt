package com.netblizzard.algorithm;

public class Fabonacci {
	/**
	 * 获取fabonacci数列中第index个数
	 * @param index		索引
	 * @return			第index对应的数据
	 */
	public static long getByIndex(int index) {
		long[] data = new long[]{1, 2,-1};
		if(index < 2) {
			return data[index];
		}
		int tmp = 2;
		while (tmp <= index) {
			data[2] = data[0] + data[1];
			data[0] = data[1];
			data[1] = data[2];
			tmp++;
		}
		return data[2];
	}
}
