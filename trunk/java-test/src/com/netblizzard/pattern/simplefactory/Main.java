package com.netblizzard.pattern.simplefactory;

public class Main {
	public static void main(String[] args) {
		Operation oper;
		oper = OperationFactory.createFactory(OperationType.ADD);
		oper.numberA = 2.0;
		oper.numberB = 4.1;
		System.out.println(oper.getResult());
	}
}
