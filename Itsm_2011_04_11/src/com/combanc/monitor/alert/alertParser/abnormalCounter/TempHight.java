/**
 * Title: 温度超阈值计数器
 * Description: 报警    
 * Copyright: Copyright (c) 2010
 * Company: Combanc
 * @author 
 * @version 
 */
package com.combanc.monitor.alert.alertParser.abnormalCounter;

public class TempHight {
	int deviceId = 0;				// 设备Id
	int	hightCount = 0;				// 错误次数
	int currTemp = 0;				// 当前温度
	boolean bPaneAlerted = false;	// 是否在报警栏上报警过
	boolean bAlerted = false;		// 是否发出过报警
	
	public TempHight(int deviceId) {
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
	
	public int getCurrTemp() {
		return currTemp;
	}
	public void setCurrTemp(int temp) {
		this.currTemp = temp;
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
