package com.combanc.monitor.pojoext;

/**
 * @author lsj
 * 
 * 原邻居表，用作存储CDP、NDP信息
 */
public class NeighborEntry {

//	FIELD0 = 接口,Variant.STRING,-1,-1,
//	FIELD1 = 接口描述,Variant.STRING,-1,-1,
//	FIELD2 = 下连IP,Variant.STRING,-1,-1,
//	FIELD3 = 下连接口描述,Variant.STRING,-1,-1,
	
	String interface_;
	String description;
	String downIp;
	String downDescription;
	
	public NeighborEntry() {
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
	public String getDownIp() {
		return downIp;
	}
	public void setDownIp(String downIp) {
		this.downIp = downIp;
	}
	public String getDownDescription() {
		return downDescription;
	}
	public void setDownDescription(String downDescription) {
		this.downDescription = downDescription;
	}
}
