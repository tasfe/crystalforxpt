package com.combanc.monitor.vo.systemParam;

import com.combanc.monitor.pojo.MonitorSystemParam;

/**
 * <p>
 * Title:系统设置
 * </p>
 * <p>
 * Description:设置警示阈值和服务器阈值
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
public class Threshold {
	
	/**CPU警示阈值（%）**/
	MonitorSystemParam cpuLimen;
	/**带宽警示阈值（%）**/
	MonitorSystemParam bandLimen;
	/**单播包警示阈值（个/ 秒）**/
	MonitorSystemParam unicastLimen;
	/**广播包警示阈值（个/ 秒）**/
	MonitorSystemParam broadcastLimen;
	/**温度警示阈值（度）**/
	MonitorSystemParam tempLimen;
	/**Arp警示阈值（个）**/
	MonitorSystemParam arpLimen;
	/**TCP连接数**/
	MonitorSystemParam tcpConn;
	/**进程数**/
	MonitorSystemParam procNum;
	/**内存使用率（%）**/
	MonitorSystemParam memRate;
	/**虚存使用率（%）**/
	MonitorSystemParam vmemRate;
	/**磁盘使用率（%）**/
	MonitorSystemParam diskRate;
	
	public Threshold(){
		
	}
	public MonitorSystemParam getCpuLimen() {
		return cpuLimen;
	}
	public void setCpuLimen(MonitorSystemParam cpuLimen) {
		this.cpuLimen = cpuLimen;
	}
	public MonitorSystemParam getBandLimen() {
		return bandLimen;
	}
	public void setBandLimen(MonitorSystemParam bandLimen) {
		this.bandLimen = bandLimen;
	}
	public MonitorSystemParam getUnicastLimen() {
		return unicastLimen;
	}
	public void setUnicastLimen(MonitorSystemParam unicastLimen) {
		this.unicastLimen = unicastLimen;
	}
	public MonitorSystemParam getBroadcastLimen() {
		return broadcastLimen;
	}
	public void setBroadcastLimen(MonitorSystemParam broadcastLimen) {
		this.broadcastLimen = broadcastLimen;
	}
	public MonitorSystemParam getTempLimen() {
		return tempLimen;
	}
	public void setTempLimen(MonitorSystemParam tempLimen) {
		this.tempLimen = tempLimen;
	}
	public MonitorSystemParam getArpLimen() {
		return arpLimen;
	}
	public void setArpLimen(MonitorSystemParam arpLimen) {
		this.arpLimen = arpLimen;
	}
	public MonitorSystemParam getTcpConn() {
		return tcpConn;
	}
	public void setTcpConn(MonitorSystemParam tcpConn) {
		this.tcpConn = tcpConn;
	}
	public MonitorSystemParam getProcNum() {
		return procNum;
	}
	public void setProcNum(MonitorSystemParam procNum) {
		this.procNum = procNum;
	}
	public MonitorSystemParam getMemRate() {
		return memRate;
	}
	public void setMemRate(MonitorSystemParam memRate) {
		this.memRate = memRate;
	}
	public MonitorSystemParam getVmemRate() {
		return vmemRate;
	}
	public void setVmemRate(MonitorSystemParam vmemRate) {
		this.vmemRate = vmemRate;
	}
	public MonitorSystemParam getDiskRate() {
		return diskRate;
	}
	public void setDiskRate(MonitorSystemParam diskRate) {
		this.diskRate = diskRate;
	}
	
	
	
	
}
