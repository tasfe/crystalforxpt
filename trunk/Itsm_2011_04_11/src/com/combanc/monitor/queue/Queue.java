package com.combanc.monitor.queue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class Queue implements Serializable {
	private static final long serialVersionUID = -2929062577734481730L;
	private QNode head;
	private QNode tail;
	private int count;

	public Queue() {
		this.count = 0;
	}

	public void enqueue(Object anObject) {
		if (this.head == null) {
			this.head = (this.tail = new QNode(anObject, null));
		} else {
			this.tail.next = new QNode(anObject, null);
			this.tail = this.tail.next;
		}
		this.count += 1;
	}

	public Object dequeue() {
		if (this.head == null) {
			throw new NoSuchElementException("No more elements");
		}
		Object retval = this.head.object;
		QNode oldHead = this.head;
		this.head = this.head.next;
		if (this.head == null)
			this.tail = null;
		else {
			oldHead.next = null;
		}
		this.count -= 1;
		return retval;
	}

	public Object firstObject() {
		if (this.head == null) {
			throw new NoSuchElementException("No more elements");
		}
		return this.head.object;
	}

	public int indexOf(Object obj) {
		if (obj == null) {
			return -1;
		}
		int i = 1;
		QNode node = this.head;
		while (node != null) {
			Object tmp = node.object;
			if (obj.equals(tmp)) {
				return i;
			}
			++i;
			node = node.next;
		}
		return -1;
	}

	public Object search(Object obj) {
		if (obj == null) {
			return null;
		}
		QNode node = this.head;
		while (node != null) {
			Object tmp = node.object;
			if (obj.equals(tmp)) {
				return tmp;
			}
			node = node.next;
		}
		return null;
	}

	public boolean isEmpty() {
		return (this.head == null);
	}

	public int size() {
		return this.count;
	}

	public void clear() {
		if (this.head != null) {
			this.head.object = null;
			this.head.next = null;
		}
		if (this.tail != null) {
			this.tail.object = null;
			this.tail.next = null;
		}
		this.head = null;
		this.tail = null;
		this.count = 0;
	}

	public List toList() {
		List l = new ArrayList();
		QNode node = this.head;
		while (node != null) {
			l.add(node.object);
			node = node.next;
		}
		return l;
	}

	final class QNode implements Serializable {
		private static final long serialVersionUID = 4852010346625618050L;
		public Object object;
		public QNode next;

		public QNode(Object paramObject, QNode paramQNode) {
			this.object = paramObject;
			this.next = next;
		}
	}
}
