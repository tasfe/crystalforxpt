package com.combanc.monitor.pojo;

import java.sql.Timestamp;

public class MonitorAccessLog implements java.io.Serializable{

	private Integer id;
	private String ip;
	private String mac;
	private String upDeviceIp;
	private String interfaceDescription;
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
	public String getUpDeviceIp() {
		return upDeviceIp;
	}
	public void setUpDeviceIp(String upDeviceIp) {
		this.upDeviceIp = upDeviceIp;
	}
	public String getInterfaceDescription() {
		return interfaceDescription;
	}
	public void setInterfaceDescription(String interfaceDescription) {
		this.interfaceDescription = interfaceDescription;
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
