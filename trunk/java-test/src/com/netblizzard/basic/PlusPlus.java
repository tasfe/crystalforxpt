package com.netblizzard.basic;

public class PlusPlus {
	public static void main(String[] args) {
		int j = 0;
		for(int i=0; i<100; i++) {
		  j = j++;
		  // ��������������Ϊ�ֽ�Ϊ���¹���
//		  int k = j; //����++ǰ�ĳ�ʼֵ
//		  j++; //ִ��++��������ʱj�Ѿ����1��
//		  j = k; //��jִ��++ǰ��ֵ����j,��ʱj��1�ֱ��0��
		}

		System.out.println(j);
	}
	
}
/*ʹ��javac�������ʹ��javap -c Test�����������鿴�����ֽ��룬���£�ֻժȡmain��������



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

   

����Ҵӵ�0�п�ʼ�����������С�����ʾջ��ջ�ĵ׶�����ߣ��������ұߣ���

0��������0ѹ��ջ��ջ���ݣ���0��

1����ջ����Ԫ�ص�����Ҳ����0�����浽�ֲ�����������ΪΪ1��Ҳ���Ǳ���i���ĵط���ջ���ݣ�����

2�����ֲ�����������Ϊ1��Ҳ���Ǳ���i����ֵѹ��ջ��ջ���ݣ���0��

3�����ֲ�����������Ϊ1��Ҳ���ǳ���i����ֵ��һ����ʱ�ֲ�����������Ϊ1��ֵ��Ҳ����i��ֵ����1��ջ���ݣ���0��

6����ջ��Ԫ�ص��������浽�ֲ�����������Ϊ1��Ҳ����i���ĵط�����ʱi�ֱ����0��ջ���ݣ�����

7����ȡ������������Ϊ2����ʾ���������Ҳ����System.out��ջԪ�أ�����

10�����ֲ�����������Ϊ1��ֵ��Ҳ����i��ѹ��ջ��ջԪ�أ���0��

11�����ó���������Ϊ3�ķ�����Ҳ����System.out.println

14������main����*/
