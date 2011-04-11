package com.combanc.monitor.pojo;

import java.sql.Timestamp;

public class MonitorBaseCpuData implements java.io.Serializable {
	protected Integer id;
	protected String ip;
	protected Float cpu;
	protected Timestamp gatherTime;
	protected Integer dataIndex = 1 ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Float getCpu() {
		return cpu;
	}
	public void setCpu(Float cpu) {
		this.cpu = cpu;
	}
	public Timestamp getGatherTime() {
		return gatherTime;
	}
	public void setGatherTime(Timestamp gatherTime) {
		this.gatherTime = gatherTime;
	}
	public Integer getDataIndex() {
		return dataIndex;
	}
	public void setDataIndex(Integer dataIndex) {
		this.dataIndex = dataIndex;
	}
	
	
}
