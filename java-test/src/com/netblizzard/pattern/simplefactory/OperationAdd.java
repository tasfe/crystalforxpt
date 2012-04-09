package com.netblizzard.pattern.simplefactory;

public class OperationAdd extends Operation {
	
	@Override
	public double getResult() {
		return numberA + numberB;
	}
}
