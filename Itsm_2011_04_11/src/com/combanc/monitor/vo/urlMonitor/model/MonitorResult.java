package com.combanc.monitor.vo.urlMonitor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.combanc.monitor.vo.snmp.entity.TrapV2PDU;


/**
 * <p>
 * Title:URL监测结果类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class MonitorResult implements Serializable {
	protected String resultDesc;
	protected String paramString;
	protected String wontedVal;
	protected String currentVal;
	protected int port;
	protected int state;
	protected PerfResult[] perfs;
	protected MonitorInstance[] dynamicInsts;
	protected MonitorInstance[] stateInsts;
	private List customTraps;

	public MonitorResult() {
		this.port = -1;

		this.state = 0;

		this.customTraps = new ArrayList();
	}

	public MonitorResult(boolean wonted) {
		this(wonted, null);
	}

	public MonitorResult(int state) {
		this(state, null);
	}

	/** @deprecated */
	public MonitorResult(boolean wonted, String resultDesc) {
		this((wonted) ? 1 : -1, resultDesc);
	}

	public MonitorResult(int state, String resultDesc) {
		this.port = -1;

		this.state = 0;

		this.customTraps = new ArrayList();

		this.state = state;
		this.resultDesc = resultDesc;
	}

	public String getResultDesc() {
		return ((this.resultDesc == null) ? "" : this.resultDesc);
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	/** @deprecated */
	public void setWonted(boolean wonted) {
		setState((wonted) ? 1 : -1);
	}

	public String getParamString() {
		return ((this.paramString == null) ? "" : this.paramString);
	}

	public void setParamString(String paramString) {
		this.paramString = paramString;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getCurrentVal() {
		return ((this.currentVal == null) ? this.resultDesc : this.currentVal);
	}

	public void setCurrentVal(String currentVal) {
		this.currentVal = currentVal;
	}

	public String getWontedVal() {
		return ((this.wontedVal == null) ? "" : this.wontedVal);
	}

	public void setWontedVal(String wontedVal) {
		this.wontedVal = wontedVal;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("state:" + this.state);
		sb.append(",desc:" + this.resultDesc);
		return sb.toString();
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public PerfResult[] getPerfResults() {
		return this.perfs;
	}

	public void setPerfResults(PerfResult[] perfs) {
		this.perfs = perfs;
	}

	public void addTrapPDU(TrapV2PDU pdu) {
		if (pdu == null) {
			return;
		}
		this.customTraps.add(pdu);
	}

	public boolean removeTrapPDU(TrapV2PDU pdu) {
		if (pdu == null) {
			return false;
		}
		return this.customTraps.remove(pdu);
	}

	public List getTrapPDUs() {
		return this.customTraps;
	}

	public MonitorInstance[] getDynamicInstances() {
		return this.dynamicInsts;
	}

	public void setDynamicInstances(MonitorInstance[] dynamicInsts) {
		this.dynamicInsts = dynamicInsts;
	}

	public void setInstanceStates(MonitorInstance[] stateInsts) {
		this.stateInsts = stateInsts;
	}

	public MonitorInstance[] getInstanceStates() {
		return this.stateInsts;
	}
}
