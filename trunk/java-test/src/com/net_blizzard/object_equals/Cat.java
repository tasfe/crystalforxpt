package com.net_blizzard.object_equals;

import java.util.Date;

public class Cat {
	/**
	 * Cat���к���name��birthday��˽�г�Ա����������д��equals������hashCode����
	 * 
	 * @param name
	 *            �� birthday
	 * 
	 */

	private String name;
	private Date birthday;

	public Cat() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

		  /*
		  * ��дequals����ע�⣺
		  *   1 �Է��ԣ��������������ֵx��x.equals(x)һ��Ϊtrue
		  *   2  �Գ��ԣ��������������ֵx �� y����x.equals(y)����true��y.equals(x)Ҳһ������true
		  *   3 �����ԣ��������������ֵx��y�ͣ������x.equals(y)����true������y.equals(z)Ҳ����true����ôx.equals(z)Ҳһ����   �� true
		  *   4 һ���ԣ��������������ֵx �� y���������equals�ȽϵĶ�����Ϣû�б��޸ģ�
		  *           ��ε���x.equals(y)Ҫôһ�µط���true��Ҫôһ�µط���false
		  *   5 �ǿ��ԣ���������ķǿ�����ֵx��x.equals(null)һ������false
		  *��
		  * ��ע�⣺
		  * ��дequals�����������дhashCode���������������ȼ۶�����ܵõ���ͬ��hashCode,���ڼ��Ͽ����ʹ�ÿ��ܲ������غ��
		  */
		 
		 
		 /*
		  *  1.��дequals�������η�������public,��Ϊ����д��Object�ķ���.
		  *  2.�������ͱ�����Object.
		  */ 
	public boolean equals(Object other) { // ��дequals���������������дhashCode����

		if (this == other) // �ȼ���Ƿ����Է��ԣ���Ƚ�other�Ƿ�Ϊ�ա�����Ч�ʸ�
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Cat))
			return false;

		final Cat cat = (Cat) other;

		if (!getName().equals(cat.getName()))
			return false;
		if (!getBirthday().equals(cat.getBirthday()))
			return false;
		return true;
	}
		 
	public int hashCode() { // hashCode��Ҫ���������hashϵͳ�Ĳ�ѯЧ�ʡ���hashCode�в������κβ���ʱ������ֱ�����䷵��
							// һ���������߲�������д��
		int result = getName().hashCode();
		result = 29 * result + getBirthday().hashCode();
		return result;
		// return 0;
	}

	public static void main(String[] args) {

		Date dayA = new Date(4000000);
		Cat a = new Cat();
		a.setName("a");
		a.setBirthday(dayA);

		Date dayB = new Date(1000000);
		Cat b = new Cat();
		b.setName("a");
		b.setBirthday(dayB);

		Date dayC = dayA;
		Cat c = new Cat();
		c.setName("a");
		c.setBirthday(dayC);

		Date dayE = dayA;
		Cat e = new Cat();
		e.setName(a.getName());
		e.setBirthday(a.getBirthday());

		Date dayD = dayC;
		Dog d = new Dog();
		d.setName("a");
		d.setBirthday(dayD);

//		false
//		true
//		true
		System.out.println(a.equals(b)); // �����Լ������������equals���� 
		System.out.println(a.equals(a));
		System.out.println(a.equals(c));

//		false
//		false
//		true
//		true
//		true
//		false
		System.out.println(d.equals(a));
		System.out.println(a.equals(d)); // ��֤��дequals�ĶԳ���
		System.out.println(a.equals(e));
		System.out.println(e.equals(c)); // ��֤��дequals�Ĵ�����
		System.out.println(d.getName().equals(a.getName())); // ����Object����equals����
		System.out.println(d.getBirthday().equals(b.getBirthday()));

		System.out.println("�Ƚ�hanshCode��ֵ");

		/*
		 * * �Ƚ�hashCode�����з��ص�ֵ ���equals����Ϊtrue����hashCodeһ������true��
		 * ���equals����Ϊfalse��hashCode����ֵ��һ������ͬ��
		 * ���hashCode����ֵ��ͬ����equals����ֵһ��Ϊfalse��
		 * ���hashCode����ֵ��ͬ����equals����ֵ��һ��Ϊfalse��
		 */
//		4002813
//		1002813
//		false
//		true
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(a.hashCode() == b.hashCode()); // ���equals����false,���hashCode��һ�����ز�ֵͬ
		System.out.println(a.hashCode() == c.hashCode());
	}
}
