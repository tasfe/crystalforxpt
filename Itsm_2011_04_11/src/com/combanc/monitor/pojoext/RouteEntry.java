package com.combanc.monitor.pojoext;

/**
 * @author lsj
 * 
 * 对应原路由表
 */
public class RouteEntry {
//	FIELD0 = 网段,Variant.STRING,-1,-1,
//	FIELD1 = 掩码,Variant.STRING,-1,-1,
//	FIELD2 = 下一跳,Variant.STRING,-1,-1,
//	FIELD3 = 类型,Variant.STRING,-1,-1,
//	FIELD4 = 接口,Variant.STRING,-1,-1,
//	FIELD5 = 接口描述,Variant.STRING,-1,-1,
//	FIELD6 = 直连路由设备,Variant.STRING,-1,-1,
//	FIELD7 = 子网,Variant.STRING,-1,-1,
	
	String segment;
	String mask;
	String nextHop;
	String type;
	String interface_;
	String description;		// 接口的描述
	String linkedDevice;
	
	public RouteEntry() {
	}
	
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public String getNextHop() {
		return nextHop;
	}
	public void setNextHop(String nextHop) {
		this.nextHop = nextHop;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getLinkedDevice() {
		return linkedDevice;
	}
	public void setLinkedDevice(String linkedDevice) {
		this.linkedDevice = linkedDevice;
	}
}
