package com.combanc.monitor.pojoext;

public class IpMacEntry {
// 对应原设备MAC表
//FIELD0 = IP,Variant.STRING,-1,-1,
//FIELD1 = MAC,Variant.STRING,-1,-1,
//FIELD2 = 子网,Variant.STRING,-1,-1,
	
	String ip;
	String mac;
	
	public IpMacEntry() {
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
}
