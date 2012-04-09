package com.netblizzard.pattern.simplefactory;

public class OperationFactory {
	public static Operation createFactory(OperationType type) {
		Operation oper;
		switch (type) {
		case ADD:
			oper = new OperationAdd();
			break;
		case SUB:
			oper = new OperationSub();
			break;
		case MUL:
			oper = new OperationMul();
			break;
		case DIV:
			oper = new OperationDiv();
			break;
		default:
			oper = new OperationAdd();
		}
		return oper;
	}
}
