package com.combanc.monitor.vo.urlMonitor.model;

import java.io.Serializable;

public class PerfResult implements Serializable {
	private static final long serialVersionUID = 1969097202468112413L;
	protected int itemIndex;
	protected String instanceKey;
	protected double value;
	protected String strValue;
	protected int state;

	public PerfResult(int monitorItem_idx, double value, boolean obtainable) {
		this.instanceKey = "";

		this.state = 1;

		this.itemIndex = monitorItem_idx;
		this.value = value;
		setObtainable(obtainable);
	}

	public PerfResult(int monitorItem_idx, String strValue, boolean obtainable) {
		this.instanceKey = "";

		this.state = 1;

		this.itemIndex = monitorItem_idx;
		this.strValue = strValue;
		setObtainable(obtainable);
	}

	public PerfResult(int monitorItem_idx, double value) {
		this(monitorItem_idx, value, true);
	}

	public PerfResult(int monitorItem_idx, boolean obtainable) {
		this(monitorItem_idx, 0.0D, obtainable);
	}

	public PerfResult(int monitorItem_idx) {
		this(monitorItem_idx, 0.0D, true);
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getItemIndex() {
		return this.itemIndex;
	}

	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}

	public boolean isObtainable() {
		return (this.state != -1);
	}

	/** @deprecated */
	public void setObtainable(boolean obtainable) {
		this.state = ((obtainable) ? 1 : -1);
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public String getInstanceKey() {
		return this.instanceKey;
	}

	public void setInstanceKey(String instanceKey) {
		this.instanceKey = ((instanceKey == null) ? "" : instanceKey);
	}

	public String getStrValue() {
		return this.strValue;
	}

	public void setStrValue(String strVlue) {
		this.strValue = strVlue;
	}
}