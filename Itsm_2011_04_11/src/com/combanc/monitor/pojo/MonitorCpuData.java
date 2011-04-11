package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorCpuData entity. @author MyEclipse Persistence Tools
 */

public class MonitorCpuData extends MonitorBaseCpuData implements java.io.Serializable {

	
	// Constructors

	/** default constructor */
	public MonitorCpuData() {
	}

	/** full constructor */
	public MonitorCpuData(String ip, Float cpu, Timestamp gatherTime) {
		this.ip = ip;
		this.cpu = cpu;
		this.gatherTime = gatherTime;
	}

	
	

}