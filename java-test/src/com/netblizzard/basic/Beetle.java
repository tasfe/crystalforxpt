package com.netblizzard.basic;

//: reusing/Beetle.java
// The full process of initialization.
class Insect {
	private int m = printInit("Insect.m Initialized");
	private int i = 9;
	protected int j;

	Insect() {
		print("i=" + i + ",j=" + j);
		j = 39;
	}

	private static int x1 = printInit("static Insect.x1 Initialized");

	static int printInit(String s) {
		print(s);
		return 47;
	}

	static void print(String string) {
		System.out.println(string);
	}
}

public class Beetle extends Insect {
	private int k = printInit("Beetle.k Initialized");

	public Beetle() {
		print("k =" + k);
		print("j =" + j);
	}

	private static int x2 = printInit("static Beetle.x2 initialized");

	public static void main(String[] args) {
		print("Beetle constructor");
		Beetle b = new Beetle();
	}
}
/*
初始化顺序：
1、类加载器首先load含有main方法的Beetle类，发现它有父类Insect，于是先去加载Insect
2、加载Insect的静态变量	static Insect.x1 Initialized
3、加载Beetle的静态变量	static Beetle.x2 initialized
4、进入main方法		Beetle constructor
5、如果不生成Beetle对象，上述语句依然会输出，即不生成对象，但加载类或者调用类的静态方法（此时其实同样加载类），依然会初始化静态变量
6、生成Beetle对象，不会再初始化静态变量。会先初始化父类Insect的变量		Insect.m Initialized
7、调用父类Insect的构造函数			i=9,j=0
8、初始化子类Beetle的变量			Beetle.k Initialized
9、调用子类Beetle的构造函数			k =47	j =39

static Insect.x1 Initialized
static Beetle.x2 initialized
Beetle constructor
Insect.m Initialized
i=9,j=0
Beetle.k Initialized
k =47
j =39
*/
