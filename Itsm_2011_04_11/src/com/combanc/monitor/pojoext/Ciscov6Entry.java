package com.combanc.monitor.pojoext;

/**
 * @author lsj
 * 
 * 原CISCOV6表
 */
public class Ciscov6Entry {

//	FIELD0 = 接口,Variant.STRING,-1,-1,
//	FIELD1 = 接口描述,Variant.STRING,-1,-1,
//	FIELD2 = MTU,Variant.STRING,-1,-1,
//	FIELD3 = 最大报文长度,Variant.STRING,-1,-1,
	
	String interface_;
	String description;
	String Mtu;
	String maxSize;
	
	public Ciscov6Entry() {
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

	public String getMtu() {
		return Mtu;
	}

	public void setMtu(String mtu) {
		Mtu = mtu;
	}

	public String getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}
}
