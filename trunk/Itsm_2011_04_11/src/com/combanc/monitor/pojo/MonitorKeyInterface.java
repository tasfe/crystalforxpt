package com.combanc.monitor.pojo;

public class MonitorKeyInterface implements java.io.Serializable{

	private Integer id;
	private MonitorDevice monitorDevice;
	private String interfaceNum;
	private Boolean isMonitor = true;
	private String interfaceDesc;
	private String note;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MonitorDevice getMonitorDevice() {
		return monitorDevice;
	}
	public void setMonitorDevice(MonitorDevice monitorDevice) {
		this.monitorDevice = monitorDevice;
	}
	public String getInterfaceNum() {
		return interfaceNum;
	}
	public void setInterfaceNum(String interfaceNum) {
		this.interfaceNum = interfaceNum;
	}
	public Boolean getIsMonitor() {
		return isMonitor;
	}
	public void setIsMonitor(Boolean isMonitor) {
		this.isMonitor = isMonitor;
	}
	public String getInterfaceDesc() {
		return interfaceDesc;
	}
	public void setInterfaceDesc(String interfaceDesc) {
		this.interfaceDesc = interfaceDesc;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
