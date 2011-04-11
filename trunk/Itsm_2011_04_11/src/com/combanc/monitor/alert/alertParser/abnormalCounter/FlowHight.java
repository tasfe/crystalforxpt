/**
 * Title: 带宽占用超阈值计数器
 * Description:  报警   
 * Copyright: Copyright (c) 2010
 * Company: Combanc
 * @author 
 * @version 
 */
package com.combanc.monitor.alert.alertParser.abnormalCounter;

public class FlowHight {
	int deviceId = 0;				// 设备Id
	int interface_ = 0;				// 接口号
	int	hightCount = 0;				// 错误次数
	int currFlowRate = 0;			// 当前带宽占用
	boolean bPaneAlerted = false;	// 是否在报警栏上报警过
	boolean bAlerted = false;		// 是否发出过报警
	
	public FlowHight(int deviceId, int interface_) {
		this.deviceId = deviceId;
		this.interface_ = interface_;
		this.hightCount = 0;
	}
	
	public Integer getDeviceId(){
		return deviceId;
	}
	public void setDeviceId(int id) {
		this.deviceId = id;
	}
	
	 
	
	public int getInterface_() {
		return interface_;
	}

	public void setInterface_(int interface1) {
		interface_ = interface1;
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
	
	public int getCurrFlowRate() {
		return currFlowRate;
	}
	public void setCurrFlowRate(int currFlowRate) {
		this.currFlowRate = currFlowRate;
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
