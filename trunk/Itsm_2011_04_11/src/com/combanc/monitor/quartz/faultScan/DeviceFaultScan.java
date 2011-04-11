package com.combanc.monitor.quartz.faultScan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.DeviceFault;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;

/**
 * <p>
 * Title:故障报警扫描
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
public class DeviceFaultScan extends BaseScan{

	private static final Log log = LogFactory.getLog(CpuHightScan.class);
	/**设备列表**/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	
	private MonitorDeviceService monitorDeviceService;
	
	private DeviceSnmpQuery deviceSnmpQuery;
	
	private Alerter alerter;
	
	public DeviceFaultScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerDeviceFaultScanTask";
	}
	
	/**初始化**/
	public void init(){
		deviceList=monitorDeviceService.findAll();
	}
	
	public void startScan(){
		System.out.println("故障报警扫描开始");
		init();
		List<DeviceFault> list = Alerter.deviceFaultList;
		if ( null == list || list.isEmpty())
			return;
		String type = "设备";
		String ret;
		synchronized (Alerter.deviceFaultList) {
			for( int i=0; i<list.size(); i++ ){
				DeviceFault df = list.get(i);

				// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
				MonitorDevice d = ParserTool.getDeviceFromList(df.getDeviceId(), deviceList);
				if( null == d ){
					list.remove(i);
					i--;
					continue;
				}
//				if (!MainFrame.subnetList.contains(d.getSubnet().getSubnetName())) {
//					continue;
//				}
				if (d.getMonitorDeviceType() != null && d.getMonitorDeviceType().getCode() == 1
						&& "忽略检查".equals(d.getNote1())) {
					System.out.print("忽略 " + d.getMonitorDeviceType().getName()
							+ " ：" + d.getIp() + " 的故障检查");
					continue;
				}
				type = d.getMonitorDeviceType() != null ? d.getMonitorDeviceType().getName() : "";
				
				ret = deviceSnmpQuery.getVendorOID(d);
				if ( null == ret  || ret.equals("超时")) {
					// 无反应者增加计数器
					df.IncreaseFaultCount();
				} else {
					// 有反应者计数器置0
					df.clearCount();
				}
			}// for
			alerter.checkDeviceFault();
		}// synchronized end
		
		System.out.println(":故障报警扫描结束");
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
