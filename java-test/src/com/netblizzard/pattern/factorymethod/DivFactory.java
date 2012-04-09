package com.netblizzard.pattern.factorymethod;

import com.netblizzard.pattern.simplefactory.Operation;
import com.netblizzard.pattern.simplefactory.OperationDiv;

public class DivFactory implements IFactory {

	public Operation createFactory() {
		return new OperationDiv();
	}
}
