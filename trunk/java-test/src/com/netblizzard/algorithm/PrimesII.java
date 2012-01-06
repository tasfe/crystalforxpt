package com.netblizzard.algorithm;

public class PrimesII {
	public static void main(String[] args) {
		int nValue = 50;
		outLoop:for (int i = 2; i <= nValue; i++) {
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					continue outLoop;
				}
			}
			System.out.println(i);
		}
	}
}