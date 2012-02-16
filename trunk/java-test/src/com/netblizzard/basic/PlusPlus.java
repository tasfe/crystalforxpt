package com.netblizzard.basic;

public class PlusPlus {
	public static void main(String[] args) {
		int j = 0;
		for(int i=0; i<100; i++) {
		  j = j++;
		  // 上述运算可以理解为分解为以下过程
//		  int k = j; //保存++前的初始值
//		  j++; //执行++操作，这时j已经变成1了
//		  j = k; //把j执行++前的值赋给j,这时j由1又变成0了
		}

		System.out.println(j);
	}
	
}
/*使用javac编译后再使用javap -c Test反编译这个类查看它的字节码，如下（只摘取main方法）：



public static void main(java.lang.String[]);

  Code:

   0:   iconst_0

   1:   istore_1

   2:   iload_1

   3:   iinc    1, 1

   6:   istore_1

   7:   getstatic       #2; //Field java/lang/System.out:Ljava/io/PrintStream;

   10:  iload_1

   11:  invokevirtual   #3; //Method java/io/PrintStream.println:(I)V

   14:  return

   

这里，我从第0行开始分析（分析中【】表示栈，栈的底端在左边，顶端在右边）：

0：将常数0压入栈，栈内容：【0】

1：将栈顶的元素弹出，也就是0，保存到局部变量区索引为为1（也就是变量i）的地方。栈内容：【】

2：将局部变量区索引为1（也就是变量i）的值压入栈，栈内容：【0】

3：将局部变量区索引为1（也就是常量i）的值加一，此时局部变量区索引为1的值（也就是i的值）是1。栈内容：【0】

6：将栈顶元素弹出，保存到局部变量区索引为1（也就是i）的地方，此时i又变成了0。栈内容：【】

7：获取常量池中索引为2所表示的类变量，也就是System.out。栈元素：【】

10：将局部变量区索引为1的值（也就是i）压入栈。栈元素：【0】

11：调用常量池索引为3的方法，也就是System.out.println

14：返回main方法*/
