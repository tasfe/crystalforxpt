package com.net_blizzard.object_equals;

import java.util.Date;

public class Cat {
	/**
	 * Cat类中含有name和birthday两私有成员变量，且重写了equals方法和hashCode方法
	 * 
	 * @param name
	 *            和 birthday
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
		  * 重写equals必须注意：
		  *   1 自反性：对于任意的引用值x，x.equals(x)一定为true
		  *   2  对称性：对于任意的引用值x 和 y，当x.equals(y)返回true，y.equals(x)也一定返回true
		  *   3 传递性：对于任意的引用值x、y和ｚ，如果x.equals(y)返回true，并且y.equals(z)也返回true，那么x.equals(z)也一定返   回 true
		  *   4 一致性：对于任意的引用值x 和 y，如果用于equals比较的对象信息没有被修改，
		  *           多次调用x.equals(y)要么一致地返回true，要么一致地返回false
		  *   5 非空性：对于任意的非空引用值x，x.equals(null)一定返回false
		  *　
		  * 请注意：
		  * 重写equals方法后最好重写hashCode方法，否则两个等价对象可能得到不同的hashCode,这在集合框架中使用可能产生严重后果
		  */
		 
		 
		 /*
		  *  1.重写equals方法修饰符必须是public,因为是重写的Object的方法.
		  *  2.参数类型必须是Object.
		  */ 
	public boolean equals(Object other) { // 重写equals方法，后面最好重写hashCode方法

		if (this == other) // 先检查是否其自反性，后比较other是否为空。这样效率高
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
		 
	public int hashCode() { // hashCode主要是用来提高hash系统的查询效率。当hashCode中不进行任何操作时，可以直接让其返回
							// 一常数，或者不进行重写。
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
		System.out.println(a.equals(b)); // 调用自己类中所定义的equals方法 
		System.out.println(a.equals(a));
		System.out.println(a.equals(c));

//		false
//		false
//		true
//		true
//		true
//		false
		System.out.println(d.equals(a));
		System.out.println(a.equals(d)); // 验证重写equals的对称性
		System.out.println(a.equals(e));
		System.out.println(e.equals(c)); // 验证重写equals的传递性
		System.out.println(d.getName().equals(a.getName())); // 调用Object类中equals方法
		System.out.println(d.getBirthday().equals(b.getBirthday()));

		System.out.println("比较hanshCode的值");

		/*
		 * * 比较hashCode方法中返回的值 如果equals返回为true，则hashCode一定返回true。
		 * 如果equals返回为false，hashCode返回值不一定不相同。
		 * 如果hashCode返回值不同，则equals返回值一定为false。
		 * 如果hashCode返回值不同，则equals返回值不一定为false。
		 */
//		4002813
//		1002813
//		false
//		true
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(a.hashCode() == b.hashCode()); // 如果equals返回false,则各hashCode不一定返回不同值
		System.out.println(a.hashCode() == c.hashCode());
	}
}
