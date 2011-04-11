package com.combanc.monitor.alert.alertParser.abnormalCounter;
/**
 * Title: URL报警计数器
 * Description: 报警    
 * Copyright: Copyright (c) 2010
 * Company: Combanc
 * @author 
 * @version 
 */
public class UrlFault {

	int urlId = 0;					// Url监测项Id
	int	faultCount = 0;				// 错误次数
	boolean bPaneAlerted = false;	// 是否在报警栏上报警过
	boolean bAlerted = false;		// 是否发出过报警
	String sIp = "";				// IP
	String sMsg = "";				// 报错信息
	
	public UrlFault(int urlId) {
		this.urlId = urlId;
		this.faultCount = 0;
	}
	public void IncreaseFaultCount() {
		faultCount++;
	}
	public void clearCount() {
		faultCount = 0;
	}
	public int getUrlId() {
		return urlId;
	}
	public void setUrlId(int urlId) {
		this.urlId = urlId;
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
	public String getsIp() {
		return sIp;
	}
	public void setsIp(String sIp) {
		this.sIp = sIp;
	}
	public String getsMsg() {
		return sMsg;
	}
	public void setsMsg(String sMsg) {
		this.sMsg = sMsg;
	}
	
	
}
