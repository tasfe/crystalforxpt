package com.combanc.monitor.vo.systemParam;

/**
 * <p>
 * Title:系统其他设置
 * </p>
 * <p>
 * Description:设置topN 、SNMP
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author 
 * @version 1.0
 */

public class OtherSetting {
	
	private String topN;		//TOP N
	private String snmpRetry;	//SNMP 重试次数
	private String snmpTimeOut; //SNMP 网络超时时间
	
	public String getTopN() {
		return topN;
	}
	public void setTopN(String topN) {
		this.topN = topN;
	}
	public String getSnmpRetry() {
		return snmpRetry;
	}
	public void setSnmpRetry(String snmpRetry) {
		this.snmpRetry = snmpRetry;
	}
	public String getSnmpTimeOut() {
		return snmpTimeOut;
	}
	public void setSnmpTimeOut(String snmpTimeOut) {
		this.snmpTimeOut = snmpTimeOut;
	}
	
	
}
