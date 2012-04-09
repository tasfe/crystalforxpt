package com.netblizzard.pattern.factorymethod;

import com.netblizzard.pattern.simplefactory.Operation;
import com.netblizzard.pattern.simplefactory.OperationMul;

public class MulFactory implements IFactory {

	public Operation createFactory() {
		return new OperationMul();
	}
}
