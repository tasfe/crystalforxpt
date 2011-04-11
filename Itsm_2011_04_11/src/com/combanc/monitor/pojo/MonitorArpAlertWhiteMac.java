package com.combanc.monitor.pojo;

public class MonitorArpAlertWhiteMac implements java.io.Serializable{

	private Integer id;
	private String mac;
	
	public MonitorArpAlertWhiteMac(){
		
	}
	public MonitorArpAlertWhiteMac(String mac){
		this.mac = mac;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
}
