package com.netblizzard.pattern.factorymethod;

import com.netblizzard.pattern.simplefactory.Operation;
import com.netblizzard.pattern.simplefactory.OperationAdd;

public class AddFactory implements IFactory {

	public Operation createFactory() {
		return new OperationAdd();
	}
}
