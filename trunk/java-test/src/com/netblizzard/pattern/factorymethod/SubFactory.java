package com.netblizzard.pattern.factorymethod;

import com.netblizzard.pattern.simplefactory.Operation;
import com.netblizzard.pattern.simplefactory.OperationSub;

public class SubFactory implements IFactory {

	public Operation createFactory() {
		return new OperationSub();
	}
}
