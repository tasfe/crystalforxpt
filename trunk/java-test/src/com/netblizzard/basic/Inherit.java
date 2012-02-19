package com.netblizzard.basic;

class SuperClass {
	SuperClass(int i) {
		System.out.println("Super Class " + i);
	}
}

public class Inherit extends SuperClass{

	Inherit(int i) {
		// 注意：此处必须显式调用父类的构造函数，因为父类有带参构造函数，也就不会生成不带参数的默认构造函数
		// 不使用super(i)，就需要调用基类的默认构造函数
		// Implicit super constructor SuperClass() is undefined. Must explicitly invoke another constructor
		super(i);
		// TODO Auto-generated constructor stub
	}
	
}
