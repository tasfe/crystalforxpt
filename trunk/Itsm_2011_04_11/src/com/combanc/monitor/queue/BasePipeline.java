package com.combanc.monitor.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BasePipeline {
	private static final Log logger = LogFactory.getLog(BasePipeline.class);
	protected String name;
	protected Queue queue;
	protected List consumers;
	private int initNum;
	private int maxThreadCount;
	private int minThreadCount;
	protected List consumerThreads;
	private int createCount;
	private int maxLength;
	private List currObjs;

	public BasePipeline(String name, int initNum) {
		this.queue = new Queue();
		this.consumers = new Vector();

		this.initNum = 1;

		this.maxThreadCount = 210;

		this.minThreadCount = 5;

		this.createCount = 0;

		this.maxLength = 2147483647;

		this.initNum = initNum;
		this.name = name;
		this.currObjs = new ArrayList();
		startConsumer();
	}

	public BasePipeline(String name) {
		this(name, 1);
	}

	public BasePipeline() {
		this("BasePipeline");
	}

	public boolean addConsumer(Consumer consumer) {
		return this.consumers.add(consumer);
	}

	public boolean removeConsumer(Consumer consumer) {
		return this.consumers.remove(consumer);
	}

	public void add(Object obj) {
		synchronized (this.queue) {
			int index;
			ConsumerThread consumerThread;
			if (this.queue.isEmpty()) {
				this.queue.enqueue(obj);
				this.queue.notifyAll();
			} else {
				this.queue.enqueue(obj);
			}

			if (this.queue.size() > this.maxLength) {
				Object o = this.queue.dequeue();
				if (logger.isDebugEnabled()) {
					logger.debug("超出队列最大值" + this.maxLength + ",对象出队:" + o);
					System.out.println("超出队列最大值" + this.maxLength + ",对象出队:" + o);
				}
			}

			if (getThreadCount() < this.minThreadCount) {
				for (index = 0; index < this.minThreadCount - getThreadCount(); ++index) {
					consumerThread = new ConsumerThread(getName()
							+ (this.createCount++), this);
					this.consumerThreads.add(consumerThread);
					consumerThread.start();
				}
			}

			if (logger.isDebugEnabled()) {
				logger.debug("队列中线程数：" + this.queue.size());
				System.out.println("队列中线程数：" + this.queue.size());
			}
			if ((this.queue.size() >= 50)
					&& (getThreadCount() < this.maxThreadCount - 5))
				for (index = 0; index < 5; ++index) {
					consumerThread = new ConsumerThread(getName()
							+ (this.createCount++), this);
					this.consumerThreads.add(consumerThread);
					consumerThread.start();
				}
		}
	}

	public Object dequeue() {
		synchronized (this.queue) {
			return this.queue.dequeue();
		}
	}

	protected Object get() {
		synchronized (this.queue) {
			if (this.queue.isEmpty())
				try {
					this.queue.wait();
				} catch (InterruptedException ex) {
				}
			if (this.queue.isEmpty()) {
				return null;
			}
			return this.queue.dequeue();
		}
	}

	public int size() {
		synchronized (this.queue) {
			return this.queue.size();
		}
	}

	public void clearQueue() {
		synchronized (this.queue) {
			this.queue.clear();
		}
	}

	public List getContent() {
		synchronized (this.queue) {
			return this.queue.toList();
		}
	}

	public int indexOf(Object obj) {
		if (obj == null) {
			return -1;
		}
		if (this.currObjs.contains(obj)) {
			return 0;
		}
		return this.queue.indexOf(obj);
	}

	public Object search(Object obj) {
		if (obj == null) {
			return null;
		}
		synchronized (this.queue) {
			return this.queue.search(obj);
		}
	}

	protected void doConsume(Object obj) {
		this.currObjs.add(obj);
		for (Iterator iter = this.consumers.iterator(); iter.hasNext();) {
			Consumer consumer = (Consumer) iter.next();
			consumer.doConsume(obj);
		}
	}

	public synchronized void startConsumer() {
		ConsumerThread consumerThread;
		if (this.consumerThreads == null) {
			this.consumerThreads = new ArrayList();
			for (int index = 0; index < this.initNum; ++index) {
				consumerThread = new ConsumerThread(getName()
						+ (this.createCount++), this);
				this.consumerThreads.add(consumerThread);
				consumerThread.start();
			}
		} else {
			Iterator iter = this.consumerThreads.iterator();
			while (iter.hasNext()) {
				consumerThread = (ConsumerThread) iter.next();
				if (!(consumerThread.isRunning()))
					consumerThread.start();
			}
		}
	}

	public synchronized void stopConsumer() {
	}

	public String getName() {
		return this.name;
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getThreadCount() {
		if (this.consumerThreads == null) {
			return 0;
		}
		return this.consumerThreads.size();
	}

	public int getMaxThreadCount() {
		return this.maxThreadCount;
	}

	public void setMaxThreadCount(int maxThreadCount) {
		this.maxThreadCount = maxThreadCount;
	}

	public List getConsumerThreads() {
		return Collections.unmodifiableList(this.consumerThreads);
	}

	public synchronized boolean removeCurrObject(Object obj) {
		if (this.currObjs != null) {
			return this.currObjs.remove(obj);
		}
		return false;
	}

	public List getCurrObjs() {
		return this.currObjs;
	}
}
