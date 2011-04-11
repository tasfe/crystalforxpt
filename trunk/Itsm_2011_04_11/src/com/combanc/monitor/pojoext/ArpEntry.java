package com.combanc.monitor.pojoext;

public class ArpEntry {
	String ip;
	String mac;
	String port;
	String interface_;
	String description;
	String uplinkDevice;

	String type;
	
	public ArpEntry() {
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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getInterface_() {
		return interface_;
	}

	public void setInterface_(String interface1) {
		interface_ = interface1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUplinkDevice() {
		return uplinkDevice;
	}

	public void setUplinkDevice(String uplinkDevice) {
		this.uplinkDevice = uplinkDevice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
