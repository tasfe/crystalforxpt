package com.netblizzard.pattern.factorymethod;

import com.netblizzard.pattern.simplefactory.Operation;

public class Main {
	public static void main(String[] args) {
		IFactory factory = new AddFactory();
		Operation oper;
		oper = factory.createFactory();
		oper.numberA = 2.0;
		oper.numberB = 4.1;
		System.out.println(oper.getResult());
	}
}
