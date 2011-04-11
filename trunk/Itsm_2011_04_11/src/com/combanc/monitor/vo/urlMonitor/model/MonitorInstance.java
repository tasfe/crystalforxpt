package com.combanc.monitor.vo.urlMonitor.model;

import java.io.Serializable;
import java.util.Comparator;

import com.combanc.monitor.util.TextUtil;


public class MonitorInstance implements Serializable, Cloneable {
	private static final long serialVersionUID = -5456348413346002959L;
	public static final Comparator NAME_ORDER_COMPARATOR = new OrderComparator(2);

	public static final Comparator NAMELEN_ORDER_COMPARATOR = new OrderComparator(3);

	public static final Comparator KEY_ORDER_COMPARATOR = new OrderComparator(1);

	protected int serviceId = -1;
	protected String instanceKey;
	protected String instanceName;
	protected String valueUnit;
	protected int state = 1;

	public String getValueUnit() {
		return this.valueUnit;
	}

	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}

	public MonitorInstance() {
	}

	public MonitorInstance(int serviceId) {
		this.serviceId = serviceId;
	}

	public MonitorInstance(int serviceId, String instanceKey) {
		this.serviceId = serviceId;
		this.instanceKey = instanceKey;
	}

	public MonitorInstance(int serviceId, String instanceKey,
			String instanceName) {
		this.serviceId = serviceId;
		this.instanceKey = instanceKey;
		this.instanceName = instanceName;
	}

	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getInstanceKey() {
		return this.instanceKey;
	}

	public void setInstanceKey(String instanceKey) {
		this.instanceKey = instanceKey;
	}

	public String getInstanceName() {
		return this.instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public String toString() {
		if (this.instanceName != null) {
			return this.instanceName;
		}
		return this.instanceKey;
	}

	public MonitorInstance copy() {
		try {
			return ((MonitorInstance) clone());
		} catch (CloneNotSupportedException e) {
		}
		return new MonitorInstance(this.serviceId, this.instanceKey,
				this.instanceName);
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	static class OrderComparator implements Comparator {
		private int orderField = 3;
		public static final int FIELD_KEY = 1;
		public static final int FIELD_NAME = 2;
		public static final int FIELD_NAMELEN = 3;

		public OrderComparator(int orderField) {
			this.orderField = orderField;
		}

		public int compare(Object obj1, Object obj2) {
			int l1;
			int l2;
			MonitorInstance mi1 = (MonitorInstance) obj1;
			MonitorInstance mi2 = (MonitorInstance) obj2;

			if (1 == this.orderField) {
				l1 = TextUtil.getRealLen(mi1.instanceKey);
				l2 = TextUtil.getRealLen(mi2.instanceKey);
				if (l1 != l2) {
					return (l1 - l2);
				}
				return mi1.instanceKey.compareToIgnoreCase(mi2.instanceKey);
			}
			if (3 == this.orderField) {
				l1 = TextUtil.getRealLen(mi1.toString());
				l2 = TextUtil.getRealLen(mi2.toString());
				if (l1 != l2) {
					return (l1 - l2);
				}
				return mi1.toString().compareToIgnoreCase(mi2.toString());
			}

			return mi1.toString().compareToIgnoreCase(mi2.toString());
		}
	}
}