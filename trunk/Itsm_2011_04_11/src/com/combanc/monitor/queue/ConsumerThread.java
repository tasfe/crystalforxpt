package com.combanc.monitor.queue;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConsumerThread extends Thread {
	private static final Log logger = LogFactory.getLog(ConsumerThread.class);
	protected boolean running;
	protected boolean idle;
	protected Object currObj;
	protected long startTime;
	protected long lastTime;
	protected BasePipeline owner;

	public ConsumerThread(BasePipeline owner) {
		this.running = false;

		this.idle = true;

		this.currObj = null;

		this.startTime = -1L;

		this.lastTime = -1L;

		this.owner = owner;
		setDaemon(true);
	}

	public ConsumerThread(String name, BasePipeline owner) {
		this(owner);
		setName(name);
	}

	public void reset() {
		this.startTime = -1L;
		this.lastTime = -1L;
		this.idle = true;
		this.currObj = null;
	}

	public void run() {
		this.running = true;
		while (this.running) {
			try {
				this.currObj = this.owner.get();

				this.idle = false;

				this.startTime = System.currentTimeMillis();
				this.lastTime = this.startTime;
				if (this.currObj != null)
					this.owner.doConsume(this.currObj);
			} catch (Throwable ex) {
				logger.error("执行队列消费操作出错,当前对象:" + this.currObj, ex);
			}

			reset();
		}
		this.running = false;
	}

	public boolean isRunning() {
		return this.running;
	}

	public boolean isIdle() {
		return this.idle;
	}

	public Object getCurrObject() {
		return this.currObj;
	}

	public long getCurrConsumeTime() {
		long st = this.startTime;
		if (st < 0L) {
			return -1L;
		}
		return (System.currentTimeMillis() - st);
	}

	public Date getLastConsumeTime() {
		if (this.lastTime < 0L) {
			return null;
		}
		return new Date(this.lastTime);
	}
}
