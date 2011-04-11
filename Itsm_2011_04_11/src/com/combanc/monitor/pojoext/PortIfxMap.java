package com.combanc.monitor.pojoext;

public class PortIfxMap {
//	String[] columnNames0 = new String[] { "IP", "端口", "接口", "接口描述", "子网", "subnetId" };
//	int columnTypes0[] = new int[] { Variant.STRING, Variant.STRING,
//			Variant.STRING, Variant.STRING, Variant.STRING, Variant.INT };
	String ip;
	String port;
	String interface_;
	String description;
	
	public PortIfxMap() {
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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
}
