package com.combanc.monitor.pojoext;

/**
 * @author lsj
 *
 * 原SFLOW表
 */
public class SflowEntry {

	//	FIELD0 = 采集器,Variant.STRING,-1,-1,
	//	FIELD1 = 地址,Variant.STRING,-1,-1,
	//	FIELD2 = 端口,Variant.STRING,-1,-1,
	
	String owner;	// sFlowRcvrOwner：采集器
	String address;
	String port;

	public SflowEntry() {
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
