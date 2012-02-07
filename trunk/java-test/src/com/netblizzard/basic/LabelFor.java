package com.netblizzard.basic;

public class LabelFor {

	public static void main(String[] args) {
		int m = 0;
		int i = 0;
		outer:
		for(m = 0;m < 10; m++) {
			print("--------------> " + m);
			inner:
			for(;i< 10;i++) {
				print("i = " + i);
				if(i == 2) {
					print("continue;");
					continue;
				}
				if(i == 3) {
					print("break;");
					i++;
					break;
				}
				if(i == 7) {
					print("continue outer;");
					i++;
					continue outer;
				}
				if(i == 8) {
					print("break outer");
					break outer;
				}
				for(int j = 0; j < 5; j++) {
					print("\tj = " + j);
					if(j == 3) {
						print("\t\tcontinue inner;");
						continue inner;
					}
				}
			}
		}
	}

	private static void print(String string) {
		System.out.println(string);
	}
	/*	result:
	--------------> 0
	i = 0
		j = 0
		j = 1
		j = 2
		j = 3
			continue inner;
	i = 1
		j = 0
		j = 1
		j = 2
		j = 3
			continue inner;
	i = 2
	continue;
	i = 3
	break;
	--------------> 1
	i = 4
		j = 0
		j = 1
		j = 2
		j = 3
			continue inner;
	i = 5
		j = 0
		j = 1
		j = 2
		j = 3
			continue inner;
	i = 6
		j = 0
		j = 1
		j = 2
		j = 3
			continue inner;
	i = 7
	continue outer;
	--------------> 2
	i = 8
	break outer
	*/
}
