package com.combanc.monitor.pojoext;

import com.borland.dx.dataset.Column;

public class InterfaceEntry {
// 对应原接口表
//	FIELD0 = 端口,Variant.STRING,-1,-1,
//	FIELD1 = 接口,Variant.STRING,-1,-1,
//	FIELD2 = 接口描述,Variant.STRING,-1,-1,
//	FIELD3 = 接口名,Variant.STRING,-1,-1,
//	FIELD4 = 接口速率,Variant.STRING,-1,-1,
//	FIELD5 = 开闭,Variant.BOOLEAN,-1,-1,
//	FIELD6 = 上线,Variant.BOOLEAN,-1,-1,
//	FIELD7 = IP,Variant.STRING,-1,-1,
//	FIELD8 = MAC,Variant.STRING,-1,-1,
//	FIELD9 = 设备互连,Variant.BOOLEAN,-1,-1,
	
	String port;
	String interface_;
	String description;
	String ifxName;
	String ifxAlias;		//接口别名
	String speed;
	boolean isOpenup;	//接口打开true，关闭false
	boolean isOnline;	//接口上线true，下线false
	String ip;
	String mac;
	boolean isLinked;	//是否设备互连
	
//	Column column3 = new Column();
//	column3.setColumnName("直连路由设备");
//	column3.setDataType(com.borland.dx.dataset.Variant.STRING);
	// L3TopDiscovery.java中使用
	String linkedDevice;
	
	public InterfaceEntry() {
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
	public String getIfxName() {
		return ifxName;
	}
	public void setIfxName(String ifxName) {
		this.ifxName = ifxName;
	}
	public String getIfxAlias() {
		return ifxAlias;
	}
	public void setIfxAlias(String ifxAlias) {
		this.ifxAlias = ifxAlias;
	}

	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public boolean isOpenup() {
		return isOpenup;
	}
	public void setOpenup(boolean isOpenup) {
		this.isOpenup = isOpenup;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
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
	public boolean isLinked() {
		return isLinked;
	}
	public void setLinked(boolean isLinked) {
		this.isLinked = isLinked;
	}

	public String getLinkedDevice() {
		return linkedDevice;
	}

	public void setLinkedDevice(String linkedDevice) {
		this.linkedDevice = linkedDevice;
	}
}
