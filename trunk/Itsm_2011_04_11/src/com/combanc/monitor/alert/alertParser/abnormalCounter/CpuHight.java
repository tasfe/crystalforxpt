/**
 * Title: 设备故障计数器
 * Description:   
 * Copyright: Copyright (c) 2010
 * Company: Combanc
 * @author 
 * @version 
 */
package com.combanc.monitor.alert.alertParser.abnormalCounter;

public class CpuHight {
	int deviceId = 0;				// 设备Id
	int	hightCount = 0;				// 错误次数
	int currCpu = 0;				// 当前CPU占用率
	boolean bPaneAlerted = false;	// 是否在报警栏上报警过
	boolean bAlerted = false;		// 是否发出过报警
	
	public CpuHight(int deviceId) {
		this.deviceId = deviceId;
		this.hightCount = 0;
	}
	
	public Integer getDeviceId(){
		return deviceId;
	}
	public void setDeviceId(int id) {
		this.deviceId = id;
	}
	
	public int getHightCount() {
		return hightCount;
	}
	public void IncreaseFaultCount() {
		hightCount++;
	}
	public void clearCount() {
		hightCount = 0;
	}
	
	public int getCurrCpu() {
		return currCpu;
	}
	public void setCurrCpu(int currCpu) {
		this.currCpu = currCpu;
	}
	
	public boolean isPaneAlerted() {
		return this.bPaneAlerted;
	}
	public void setPaneAlerted(boolean bPaneAlerted) {
		this.bPaneAlerted = bPaneAlerted;
	}
	
	public boolean isAlerted() {
		return this.bAlerted;
	}
	public void setAlerted(boolean bAlerted) {
		this.bAlerted = bAlerted;
	}
}
