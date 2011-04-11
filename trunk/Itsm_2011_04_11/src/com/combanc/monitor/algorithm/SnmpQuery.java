package com.combanc.monitor.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorDeviceType;

public class SnmpQuery extends Thread{
	
	String sip,eip,commStr;
	List<MonitorDevice> monitorDeviceList = new ArrayList<MonitorDevice>();
	List<MonitorDevice> newMonitorDeviceList = new ArrayList<MonitorDevice>();
	List<MonitorDeviceType> monitorDeviceTypeList = new ArrayList<MonitorDeviceType>();
	
	public SnmpQuery(){
		
	}
	
	public SnmpQuery(String sip, String eip, String commStr,
			List<MonitorDevice> monitorDeviceList, List<MonitorDeviceType> monitorDeviceTypeList, List<MonitorDevice> newMonitorDeviceList){
		this.sip = sip;
		this.eip = eip;
		this.commStr = commStr;
		this.monitorDeviceList = monitorDeviceList;
		this.monitorDeviceTypeList = monitorDeviceTypeList;
		this.newMonitorDeviceList = newMonitorDeviceList;
	}
	
	public void run(){
		SnmpDeviceScan scan = new SnmpDeviceScan();
		scan.snmpScanSubnet(sip, eip, commStr, monitorDeviceList, monitorDeviceTypeList, newMonitorDeviceList);
	}
}
