package com.netblizzard.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Primes {
	public static void main(String[] args) {
		int nValue = 50;
		List<Integer> result = new ArrayList<Integer>(); 
		boolean prime;
		for (int i = 2; i <= nValue; i++) {
			prime = true;
			for(Integer j : result) {
				if(i % j == 0) {
					prime = false;
					break;
				}
			}
			if(prime) {
				result.add(i);
				System.out.println(i);
			}
		}
	}
}