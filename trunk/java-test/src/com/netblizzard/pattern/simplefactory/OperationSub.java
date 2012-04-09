package com.netblizzard.pattern.simplefactory;

public class OperationSub extends Operation {
	
	@Override
	public double getResult() {
		return numberA + numberB;
	}
}
