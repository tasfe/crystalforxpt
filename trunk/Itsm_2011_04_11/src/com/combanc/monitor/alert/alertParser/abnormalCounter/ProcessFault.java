/**
 * Title: 服务器进程
 * Description:   报警  
 * Copyright: Copyright (c) 2010
 * Company: Combanc
 * @author 
 * @version 
 */
package com.combanc.monitor.alert.alertParser.abnormalCounter;

public class ProcessFault {
	
	int deviceId = 0;				// 设备Id
	String procName = "";			// 进程名称
	int	faultCount = 0;				// 进程不存在次数
	boolean bPaneAlerted = false;	// 是否在报警栏上报警过
	boolean bAlerted = false;		// 是否发出过报警
	boolean bMonitor = true;		// 是否被暂停监控
	
	public ProcessFault(int deviceId, String procName) {
		this.deviceId = deviceId;
		this.procName = procName;
		this.faultCount = 0;
	}
	
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
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
		bPaneAlerted = paneAlerted;
	}
	public boolean isAlerted() {
		return bAlerted;
	}
	public void setAlerted(boolean alerted) {
		bAlerted = alerted;
	}

	public boolean isbPaneAlerted() {
		return bPaneAlerted;
	}

	public void setbPaneAlerted(boolean bPaneAlerted) {
		this.bPaneAlerted = bPaneAlerted;
	}

	public boolean isbAlerted() {
		return bAlerted;
	}

	public void setbAlerted(boolean bAlerted) {
		this.bAlerted = bAlerted;
	}

	public boolean isbMonitor() {
		return bMonitor;
	}

	public void setbMonitor(boolean bMonitor) {
		this.bMonitor = bMonitor;
	}
	
}
