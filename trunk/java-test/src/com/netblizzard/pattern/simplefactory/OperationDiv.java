package com.netblizzard.pattern.simplefactory;

public class OperationDiv extends Operation {

	@Override
	public double getResult() {
		double result = 0;
		if (numberB == 0) {
			try {
				throw new Exception("除数不能为0！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			result = numberA / numberB;
		}
		return result;
	}
}
