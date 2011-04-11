/**
 * Title: 服务无反应计数器
 * Description:   报警  
 * Copyright: Copyright (c) 2010
 * Company: Combanc
 * @author 
 * @version 
 */
package com.combanc.monitor.alert.alertParser.abnormalCounter;

public class ServiceFault {
	
	int deviceId = 0;				// 设备Id
	int servicePort = 0;			// 服务端口
	String serviceName = "";		// 服务名称
	int	faultCount = 0;				// 错误次数
	boolean bPaneAlerted = false;	// 是否在报警栏上报警过
	boolean bAlerted = false;		// 是否发出过报警
	
	public ServiceFault(int deviceId, int servicePort, String serviceName) {
		this.deviceId = deviceId;
		this.servicePort = servicePort;
		this.serviceName = serviceName;
		this.faultCount = 0;
	}
	
	public Integer getDeviceId(){
		return this.deviceId;
	}
	public void setDeviceId(int id) {
		this.deviceId = id;
	}
	
	public Integer getServicePort(){
		return this.servicePort;
	}
	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}
	
	public String getServiceName(){
		return this.serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public int getFaultCount() {
		return faultCount;
	}
	public void IncreaseFaultCount() {
		faultCount++;
	}
	public void clearCount() {
		faultCount = 0;
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
