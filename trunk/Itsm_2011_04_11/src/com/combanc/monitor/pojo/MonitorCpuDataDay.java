package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorCpuDataDay entity. @author MyEclipse Persistence Tools
 */

public class MonitorCpuDataDay  extends MonitorBaseCpuData implements java.io.Serializable {

	// Fields

	
	private Float maxCpu;
	private Float minCpu;
	

	// Constructors

	/** default constructor */
	public MonitorCpuDataDay() {
	}

	/** full constructor */
	public MonitorCpuDataDay(String ip, Float cpu, Float maxCpu, Float minCpu,
			Timestamp gatherTime) {
		this.ip = ip;
		this.cpu = cpu;
		this.maxCpu = maxCpu;
		this.minCpu = minCpu;
		this.gatherTime = gatherTime;
	}

	public Float getMaxCpu() {
		return this.maxCpu;
	}

	public void setMaxCpu(Float maxCpu) {
		this.maxCpu = maxCpu;
	}

	public Float getMinCpu() {
		return this.minCpu;
	}

	public void setMinCpu(Float minCpu) {
		this.minCpu = minCpu;
	}


}