package com.netblizzard.basic;

/**
 * 只有在给数组中的对象引用实际赋值时，才会调用构造函数。
 * @author Administrator
 *
 */
public class ArrayOfObject {
	public ArrayOfObject(String str) {
		System.out.println("construct " + str);
	}
	
	public static void main(String[] args) {
		ArrayOfObject[] arr = new ArrayOfObject[10];
		int i = 0;
		for(ArrayOfObject obj : arr) {
			obj = new ArrayOfObject("obj " + i);
			i++;
		}
	}

}
