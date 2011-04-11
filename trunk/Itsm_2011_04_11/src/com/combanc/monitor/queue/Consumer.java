package com.combanc.monitor.queue;

public abstract interface Consumer {
	public abstract boolean doConsume(Object paramObject);
}
