package com.combanc.monitor.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojoext.InterfaceEntry;
import com.combanc.monitor.pojoext.MacEntry;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorInterfaceCacheService;
import com.combanc.monitor.service.MonitorSubnetService;

/**
 * <p>
 * Title:发现互连接口
 * </p>
 * <p>
 * Description:根据上连IP和下连IP自动发现互联接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: combanc
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */
public class PortMatch {
	public String highDev = null ,highIf = "", highIfDescr = "";
	public String lowDev = null,lowIf = "", lowIfDescr = "";
	 
	int subnetId;
	//下位MAC地址集合
	HashSet lowMacs =new HashSet();
	//分区内路由类型设备网关MAC集合
	HashSet routerMacs =new HashSet();
	//设备列表
	private List<MonitorDevice> deviceList;
	//上位设备AFT表
	List<MacEntry> highDevMacEntryList = new ArrayList<MacEntry>();
	//下位设备AFT表
	List<MacEntry> lowDevMacEntryList = new ArrayList<MacEntry>();

	private MonitorSubnetService monitorSubnetService;
	private DeviceSnmpQuery deviceSnmpQuery;
	private MACPortScan macPortScan ;
	
	

	public MACPortScan getMacPortScan() {
		return macPortScan;
	}

	public void setMacPortScan(MACPortScan macPortScan) {
		this.macPortScan = macPortScan;
	}

	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}

	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
	}

	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}

	public boolean portMatch(int subnetId,String highDev,String lowDev) {
		highIf = ""; 
		highIfDescr = "";
		lowIf = "";
		lowIfDescr = "";
		lowMacs.clear();
		routerMacs.clear();
		highDevMacEntryList.clear();
		lowDevMacEntryList.clear();
		try {
			macPortScan.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.highDev = highDev;
		this.lowDev = lowDev;
		this.subnetId = subnetId;
		deviceList = monitorSubnetService.getSelectedDeviceBySubnet(subnetId);
		if(deviceList.size()>0)
			return portMatch();
		else
			return false;
	
	}
	
	public boolean portMatch() {
		// 读取分区所有路由类型设备得到网关MAC集合，读取下位设备得到下位MAC地址集合
		if (!getMacSet()) {
			return false;
		}
		// 读取上位设备和下位设备的MAC转发表
		for (MonitorDevice d : deviceList) {
			if(null != d.getMonitorDeviceType() && MainConstants.isSwitch(d.getMonitorDeviceType().getCode())){
				if (d.getIp().equals(highDev)) {
					readDevMac(d,highDevMacEntryList);
				}else if(d.getIp().equals(lowDev)){
					readDevMac(d,lowDevMacEntryList);
				}
			}
			
		}
		
		if (highDevMacEntryList.isEmpty() && lowDevMacEntryList.isEmpty()) {
			return false;
		}
		// 如果上位设备的某个端口看到了下位设备，那么它是上位互连口；
		// 如果下位设备的某个端口看到了网管，那么它是下位互连口；
		// 算法仅当网管站位于上位设备或其上成立。
		if (!highDevMacEntryList.isEmpty() && !lowMacs.isEmpty()) {
			for(MacEntry entry : highDevMacEntryList){
				if(lowMacs.contains(entry.getMac())){
					highIf = entry.getInterface_();
					highIfDescr = entry.getDescription();
					break;
				}
			}
		}
		
		if (!lowDevMacEntryList.isEmpty() && !routerMacs.isEmpty()) {
			for(MacEntry entry : lowDevMacEntryList){
				if(routerMacs.contains(entry.getMac())){
					lowIf = entry.getInterface_();
					lowIfDescr = entry.getDescription();
					break;
				}
			}
		}
		
		return true;
	}
	
	private void readDevMac(MonitorDevice d,List<MacEntry> devMacEntryList){
		boolean MIndex = false;
		boolean PIndex = false;
		String note2 = "";
		if (d.getMonitorVendorMac() != null) {
			if (d.getMonitorVendorMac().getMac().length() > 0
					&& d.getMonitorVendorMac().getMac().equals("m")) {
				MIndex = true;
			}
			if (d.getMonitorVendorMac().getMac().length() > 0
					&& d.getMonitorVendorMac().getMac().equals("p")) {
				PIndex = true;
			}
			if (d.getMonitorVendorMac().getMac().length() > 0
					&& !d.getMonitorVendorMac().getMac().equals("m")
					&& !d.getMonitorVendorMac().getMac().equals("p")) {
				note2 = d.getMonitorVendorMac().getMac().toUpperCase();
			}
		}
		macPortScan.scanDevice(d,
				MIndex,// MIndex
				PIndex,// PIndex
				note2,  
				1,
				devMacEntryList);

	}
	/**
	 * 读取分区所有路由类型设备得到网关MAC集合，
	 * 读取下位设备得到下位MAC地址集合
	 * @return
	 */
	private boolean getMacSet() {
		String curDev, ret;
		for (MonitorDevice d : deviceList) {
			curDev = d.getIp();
			if (curDev.equals(lowDev) || (null != d.getMonitorDeviceType() && MainConstants.isRouter(d.getMonitorDeviceType().getCode()))) {
				//设备接口列表
				List<InterfaceEntry> interfaceEntryList = new ArrayList<InterfaceEntry>();
				ret = deviceSnmpQuery.getIFMACTable(d, interfaceEntryList);
				if (!ret.equals("OK"))
					continue;
				for(InterfaceEntry entry : interfaceEntryList){
					if (curDev.equals(lowDev)){
						lowMacs.add(entry.getMac());
					}else{
						routerMacs.add(entry.getMac());
					}
				}
				 
				 
			}
		}
		if (lowMacs.isEmpty() && routerMacs.isEmpty())
			return false;
		else
			return true;
	}
	
	
}
