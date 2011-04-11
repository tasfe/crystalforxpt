package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorCpuDataHour entity. @author MyEclipse Persistence Tools
 */

public class MonitorCpuDataHour  extends MonitorBaseCpuData implements java.io.Serializable {

	// Fields

	
	private Float maxCpu;
	private Float minCpu;
	
	// Constructors

	/** default constructor */
	public MonitorCpuDataHour() {
	}

	/** full constructor */
	public MonitorCpuDataHour(String ip, Float cpu, Float maxCpu, Float minCpu,
			Timestamp gatherTime) {
		this.ip = ip;
		this.cpu = cpu;
		this.maxCpu = maxCpu;
		this.minCpu = minCpu;
		this.gatherTime = gatherTime;
	}

	public Float getMaxCpu() {
		return maxCpu;
	}

	public void setMaxCpu(Float maxCpu) {
		this.maxCpu = maxCpu;
	}

	public Float getMinCpu() {
		return minCpu;
	}

	public void setMinCpu(Float minCpu) {
		this.minCpu = minCpu;
	}

	

}