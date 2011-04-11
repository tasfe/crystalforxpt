package com.combanc.monitor.vo.systemParam;

import com.combanc.monitor.pojo.MonitorSystemParam;

/**
 * <p>
 * Title:系统设置:计算机扫描
 * </p>
 * <p>
 * Description:设置扫描间隔、选项等
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
public class ComputerScan {
	
	/**间隔**/
	String scanGapTime;
	/**定时扫描时间1**/
	String scanHour0;
	/**定时扫描时间2**/
	String scanHour1;
	/**定时扫描时间3**/
	String scanHour2;
	/**Netbios端口**/
	String arpNbt;
	/**Ping预扫描**/
	String pingScan;
	/**忽略Mac转发表异常**/
	String ignoreError;
	
	/**扫描MAC转发表&ARP表 ， 替换l2Scan，l3Scan **/
	String l23Scan;

	/**策略:报警并归档，不报警但归档**/
	String changeRefresh;
	
	/**报警：计算机信息改变后报警**/
	String changeAlert;
	/**报警：新计算机**/
	String newHost;
	/**报警：IP地址改变**/
	String ipChangedAlert;
	/**报警：登录名改变**/
	String userChangedAlert;
	/**报警：计算机名改变**/
	String computerNameChangedAlert;
	/**报警：域或组改变**/
	String domainChangedAlert;
	/**报警：交换机接口改变**/
	String linkChangedAlert;
	
	public ComputerScan(){
		
	}

	public String getScanGapTime() {
		return scanGapTime;
	}

	public void setScanGapTime(String scanGapTime) {
		this.scanGapTime = scanGapTime;
	}

	public String getScanHour0() {
		return scanHour0;
	}

	public void setScanHour0(String scanHour0) {
		this.scanHour0 = scanHour0;
	}

	public String getScanHour1() {
		return scanHour1;
	}

	public void setScanHour1(String scanHour1) {
		this.scanHour1 = scanHour1;
	}

	public String getScanHour2() {
		return scanHour2;
	}

	public void setScanHour2(String scanHour2) {
		this.scanHour2 = scanHour2;
	}

	public String getArpNbt() {
		return arpNbt;
	}

	public void setArpNbt(String arpNbt) {
		this.arpNbt = arpNbt;
	}

	public String getPingScan() {
		return pingScan;
	}

	public void setPingScan(String pingScan) {
		this.pingScan = pingScan;
	}

	public String getIgnoreError() {
		return ignoreError;
	}

	public void setIgnoreError(String ignoreError) {
		this.ignoreError = ignoreError;
	}

	

	public String getL23Scan() {
		return l23Scan;
	}

	public void setL23Scan(String l23Scan) {
		this.l23Scan = l23Scan;
	}

	public String getChangeRefresh() {
		return changeRefresh;
	}

	public void setChangeRefresh(String changeRefresh) {
		this.changeRefresh = changeRefresh;
	}

	public String getChangeAlert() {
		return changeAlert;
	}

	public void setChangeAlert(String changeAlert) {
		this.changeAlert = changeAlert;
	}

	public String getNewHost() {
		return newHost;
	}

	public void setNewHost(String newHost) {
		this.newHost = newHost;
	}

	public String getIpChangedAlert() {
		return ipChangedAlert;
	}

	public void setIpChangedAlert(String ipChangedAlert) {
		this.ipChangedAlert = ipChangedAlert;
	}

	public String getUserChangedAlert() {
		return userChangedAlert;
	}

	public void setUserChangedAlert(String userChangedAlert) {
		this.userChangedAlert = userChangedAlert;
	}

	public String getComputerNameChangedAlert() {
		return computerNameChangedAlert;
	}

	public void setComputerNameChangedAlert(String computerNameChangedAlert) {
		this.computerNameChangedAlert = computerNameChangedAlert;
	}

	public String getDomainChangedAlert() {
		return domainChangedAlert;
	}

	public void setDomainChangedAlert(String domainChangedAlert) {
		this.domainChangedAlert = domainChangedAlert;
	}

	public String getLinkChangedAlert() {
		return linkChangedAlert;
	}

	public void setLinkChangedAlert(String linkChangedAlert) {
		this.linkChangedAlert = linkChangedAlert;
	}
	
	 
	
	
}
