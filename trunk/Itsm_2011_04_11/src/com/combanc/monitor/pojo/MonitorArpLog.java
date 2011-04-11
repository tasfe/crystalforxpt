package com.combanc.monitor.pojo;

import java.sql.Timestamp;

public class MonitorArpLog implements java.io.Serializable{

	private Integer id;
	private String ip;
	private String mac;
	private String type;
	private Timestamp firstTime;
	private Timestamp lastTime;
	
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
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(Timestamp firstTime) {
		this.firstTime = firstTime;
	}
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	
	
}
