package com.combanc.monitor.alert.alertParser.abnormalCounter;

import com.combanc.monitor.constants.MainConstants;

public class KeyInterfaceFault {

	int deviceId = 0;				// 设备Id
	String interfaceNum = "";		// 接口号
	String interfaceDesc = "";		// 接口描述
	String interfaceNote = "";		// 接口备注
	
	int faultType = MainConstants.INTERFACE_STATUS_OFFLINE;
	
	int	faultCount = 0;				// 接口异常次数
	boolean bPaneAlerted = false;	// 是否在报警栏上报警过
	boolean bAlerted = false;		// 是否发出过报警
	boolean bMonitor = true;		// 是否被暂停监控
	
	public KeyInterfaceFault(int deviceId, String interfaceNum,
			String interfaceDesc, String interfaceNote, int faultType) {
		this.deviceId = deviceId;
		this.interfaceNum = interfaceNum;
		this.faultCount = 0;
		this.interfaceDesc = interfaceDesc;
		this.interfaceNote = interfaceNote;
		this.faultType = faultType;
	}
	
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getInterfaceNum() {
		return interfaceNum;
	}
	public void setInterfaceNum(String interfaceNum) {
		this.interfaceNum = interfaceNum;
	}
	
	public String getInterfaceDesc() {
		return interfaceDesc;
	}
	public void setInterfaceDesc(String interfaceDesc) {
		this.interfaceDesc = interfaceDesc;
	}
	
	public String getInterfaceNote() {
		return interfaceNote;
	}
	public void setInterfaceNote(String interfaceNote) {
		this.interfaceNote = interfaceNote;
	}
	
	public int getFaultType() {
		return faultType;
	}
	public void setFaultType(int faultType) {
		this.faultType = faultType;
	}
	
	public int getFaultCount() {
		return faultCount;
	}
	public void setFaultCount(int faultCount) {
		this.faultCount = faultCount;
	}
	public void IncreaseFaultCount() {
		faultCount++;
	}
	public void clearCount() {
		faultCount = 0;
	}
	
	public boolean isPaneAlerted() {
		return bPaneAlerted;
	}
	public void setPaneAlerted(boolean paneAlerted) {
		this.bPaneAlerted = paneAlerted;
	}
	
	public boolean isAlerted() {
		return bAlerted;
	}
	public void setAlerted(boolean alerted) {
		this.bAlerted = alerted;
	}
	
	public boolean isMonitor() {
		return this.bMonitor;
	}
	public void setMonitor(boolean bMonitor) {
		this.bMonitor = bMonitor;
	}
}
