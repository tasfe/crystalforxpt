package com.netblizzard.pattern.simplefactory;

public class OperationMul extends Operation {
	
	@Override
	public double getResult() {
		return numberA + numberB;
	}
}
