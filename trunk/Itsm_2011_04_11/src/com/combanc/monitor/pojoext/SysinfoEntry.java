package com.combanc.monitor.pojoext;

/**
 * @author lsj
 *	
 *	对应原系统信息表
 */
public class SysinfoEntry {

//	FIELD0 = 运行时间,Variant.STRING,-1,-1,
//	FIELD1 = 描述,Variant.STRING,-1,-1,
//	FIELD2 = 联系,Variant.STRING,-1,-1,
//	FIELD3 = 名称,Variant.STRING,-1,-1,
//	FIELD4 = 位置,Variant.STRING,-1,-1,
	String runtime;
	String description;
	String contact;
	String name;
	String location;
	
	public SysinfoEntry() {
	}
	
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
