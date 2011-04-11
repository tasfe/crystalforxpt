package com.combanc.monitor.quartz.faultScan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.abnormalCounter.CpuHight;
/**
 * <p>
 * Title:CPU阈值报警扫描
 * </p>
 * <p>
 * Description:
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
public class CpuHightScan extends BaseScan{
	
	private static final Log log = LogFactory.getLog(CpuHightScan.class);
	/**设备列表**/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	
	private MonitorDeviceService monitorDeviceService;
	
	private DeviceSnmpQuery deviceSnmpQuery;
	
	private Alerter alerter;
	
	public CpuHightScan(){
		 paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		 defaultCronExpression = "0 0/1 * * * ?";
		 cronTriggerName ="cronTriggerCpuHightScanTask";
	}
	
	/**初始化**/
	public void init(){
		deviceList = monitorDeviceService.getAll();
		 
	}
	
	public void startScan(){
		System.out.println("CPU阈值报警扫描开始");
		init();
		List<CpuHight> list = Alerter.cpuHightList;
		if (list == null || list.isEmpty())
			return;
		String cpuLoadOid;
		String ret;
		synchronized (Alerter.cpuHightList) {
			for (int i = 0; i < list.size(); i++) {
				CpuHight ch = list.get(i);
				// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
				MonitorDevice d = ParserTool.getDeviceFromList(ch.getDeviceId(), deviceList);
				if (d == null) {
					list.remove(i);
					i--;
					continue;
				}
//				if (!MainFrame.subnetList.contains(d.getSubnet().getSubnetName())) {
//					continue;
//				}
				// 获取CPU占用率
				cpuLoadOid = deviceSnmpQuery.getVendorCpuOid(d);
				ret = deviceSnmpQuery.getCpuRunTime(d, cpuLoadOid);
				
				// 转换CPU占用率
				int cpuValue = 0;
				int cpuLimen;
				try{
					cpuValue = Integer.parseInt(ret);
					cpuLimen=Integer.parseInt( monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CPU_LIMEN).getValue());
				} catch (NumberFormatException e){
					e.printStackTrace();
					continue;
				}
				
				// 维护CPU超阈值计数器
				if ( cpuValue > cpuLimen) {
					// 如果超过，增加计数器
					ch.IncreaseFaultCount();
					ch.setCurrCpu(cpuValue);
				} else {
					// 如果没超过，计数器置0
					ch.clearCount();
					ch.setCurrCpu(cpuValue);
				}
			}// for end
				
			alerter.checkCpuHight();
		}// synchronized end
			
		System.out.println("CPU阈值报警扫描开始结束");	
	}

	public List<MonitorDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<MonitorDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}

	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}

	public Alerter getAlerter() {
		return alerter;
	}

	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}
	
	
}
