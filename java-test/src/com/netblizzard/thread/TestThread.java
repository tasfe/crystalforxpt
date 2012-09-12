package com.netblizzard.thread;

public class TestThread {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		TestThreadPo obj = new TestThreadPo();
		while(true) {
			obj.run();
			Thread.sleep(2000);
		}
	}

}
