package com.netblizzard.algorithm;

/**
 * 吸血鬼数字是指位数为偶数的数字，可以由一对数字相乘而得到，而这对数字各包含乘积的一半位数的数字，
 * 其中从最初的数字中选取的数字可以任意排序。以两个0结尾的数字是不允许的，例如，下列数字都是“吸血鬼”数字： 　　
 * 1260 = 21 * 60 　　
 * 1827 = 21 * 87 　　
 * 2187 = 27 * 81 　　
 * 1994年柯利弗德·皮寇弗在Usenet社群sci.math的文章中首度提出吸血鬼数。后来皮寇弗将吸血鬼数写入他的书Keys to
 * Infinity的第30章。 　　
 * 最初几个吸血鬼数为： 　　1260, 1395, 1435, 1530, 1827, 2187, 6880,
 * 102510, 104260, 105210, 105264, 105750, 108135, 110758, 115672, 116725,
 * 117067, 118440, 120600, 123354, 124483, 125248, 125433, 125460, 125500, ..
 * 
 * @author Administrator
 * 
 */
public class Vampire {
	public static void findVampire(int len) {
		int begin = (int) Math.pow(10, len - 1);
		int end = (int) Math.pow(10, len) - 1;
		System.out.println("traverse from " + begin + " to " + end);
		for (int num = begin; num <= end; num++) {
			if (num % 100 == 0) {
				continue;
			}
			int[] digits = getDigit(num, len);
			outer: for (int m = 0; m < len; m++) {
				for (int n = 0; n < len; n++) {
					if (n == m)
						continue;
					for (int mm = 0; mm < len; mm++) {
						if (mm == m || mm == n)
							continue;
						for (int nn = 0; nn < len; nn++) {
							if (nn == mm || nn == m || nn == n)
								continue;
							if ((digits[m] * 10 + digits[n]) * (digits[mm] * 10 + digits[nn]) == num) {
								System.out.println(num + " = " + digits[m] + digits[n] + " * " + digits[mm]
										+ digits[nn]);
								// 找到结果之后可以退出，否则会找出重复结果，如1260 = 21 * 60， 1260 = 60 * 21
								break outer;
							}
						}
					}
				}
			}
		}
	}

	private static int[] getDigit(int number, int len) {
		int[] result = new int[len];
		for (int i = len - 1; i >= 0; i--) {
			result[i] = number % 10;
			// System.out.println(result[i]);
			number = (number - result[i]) / 10;
		}
		return result;
	}

	public static void main(String[] args) {
		Vampire.findVampire(4);
	}
}
/*
	result: 所有 4位吸血鬼数字
	traverse from 1000 to 9999
	1260 = 21 * 60
	1395 = 15 * 93
	1435 = 41 * 35
	1530 = 51 * 30
	1827 = 87 * 21
	2187 = 27 * 81
	6880 = 86 * 80
*/

