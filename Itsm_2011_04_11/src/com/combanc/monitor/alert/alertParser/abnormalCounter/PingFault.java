package com.combanc.monitor.alert.alertParser.abnormalCounter;

import com.combanc.monitor.pojo.MonitorPingDest;

public class PingFault {

	MonitorPingDest pingDest = null;		// Ping目标
	int	faultCount = 0;				// 错误次数
	boolean bPaneAlerted = false;	// 是否在报警栏上报警过
	boolean bAlerted = false;		// 是否发出过报警
	
	public PingFault(MonitorPingDest pingDest) {
		this.pingDest = pingDest;
		this.faultCount = 0;
	}

	public MonitorPingDest getPingDest() {
		return pingDest;
	}

	public void setPingDest(MonitorPingDest pingDest) {
		this.pingDest = pingDest;
	}

	public int getFaultCount() {
		return faultCount;
	}

	public void setFaultCount(int faultCount) {
		this.faultCount = faultCount;
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
	
	public void increaseFaultCount() {
		faultCount++;
	}
	
	public void clearCount() {
		faultCount = 0;
	}
	
}
