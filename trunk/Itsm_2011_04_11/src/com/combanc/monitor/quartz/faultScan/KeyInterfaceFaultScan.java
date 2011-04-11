package com.combanc.monitor.quartz.faultScan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.KeyInterfaceFault;
import com.combanc.monitor.algorithm.DataPollTools;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;

/**
 * <p>
 * Title:关键接口下线报警
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

public class KeyInterfaceFaultScan extends BaseScan{

	private static final Log log = LogFactory.getLog(KeyInterfaceFaultScan.class);
	
	/**设备列表**/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	
	private MonitorDeviceService monitorDeviceService;	
	
	private DeviceSnmpQuery deviceSnmpQuery;
	private Alerter alerter;
	
	public KeyInterfaceFaultScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerKeyInterfaceFaultScanTask";
	}
	
	/**初始化**/
	public void init(){
		deviceList = monitorDeviceService.getAll();
	}
	
	public void startScan(){
		System.out.println("关键接口下线报警开始");
		init();
		List<KeyInterfaceFault> keyInterfaceFaultList = Alerter.keyInterfaceFaultList;
		if (keyInterfaceFaultList == null || keyInterfaceFaultList.isEmpty())
			return;
		synchronized (Alerter.keyInterfaceFaultList) {
			for (int i=0; i<keyInterfaceFaultList.size(); i++) {
				KeyInterfaceFault kif = keyInterfaceFaultList.get(i);
				if (!kif.isMonitor())	// 是否处于被监控状态
					continue;
				int deviceId = kif.getDeviceId();
				// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
				MonitorDevice dv = ParserTool.getDeviceFromList(deviceId, deviceList);
				if(null == dv || null == kif.getInterfaceNum() || kif.getInterfaceNum().isEmpty()){
					keyInterfaceFaultList.remove(i);
					i--;
					continue;
				}
				String ret = deviceSnmpQuery.getVendorOID(dv);
				if(ret == null || "超时".equals(ret))
					continue;
				int interfaceStatus = monitorDeviceService.getInterfaceStatus(dv, kif.getInterfaceNum());
				if( -1 == interfaceStatus ) {	// 获取失败
					continue;
				} else if (interfaceStatus != MainConstants.INTERFACE_STATUS_ONLINE) {
					kif.IncreaseFaultCount();
					kif.setFaultType(interfaceStatus);
				} else {
					// 计数器清零
					kif.clearCount();
				}
			} // for end 
			alerter.checkKeyInterfaceFault();
		}// synchronized end 
		
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
