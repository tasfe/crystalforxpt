package com.combanc.monitor.quartz.faultScan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.PingFault;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorPingDest;
import com.combanc.monitor.pojo.MonitorPingResult;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorPingDestService;
import com.combanc.monitor.util.PingTools;

public class PingDeviceFaultScan extends BaseScan{
	private static final Log log = LogFactory.getLog(PingDeviceFaultScan.class);
	/**设备列表**/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	
	private MonitorDeviceService monitorDeviceService;
	
	private Alerter alerter;
	
	public PingDeviceFaultScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerPingDeviceFaultScanTask";
	}
	
	/**初始化**/
	public void init(){
		deviceList=monitorDeviceService.findAll();
	}
	public void startScan(){
		System.out.println("start ping scan ......");
		init();
		if (deviceList == null || deviceList.isEmpty()) 
			return;
		List<PingFault> pingFaultList = Alerter.pingDeviceFaultList;
		if ( null == pingFaultList|| pingFaultList.isEmpty())
			return;
		for( int i=0; i<pingFaultList.size(); i++ ){
			PingFault pf = pingFaultList.get(i);
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice d = ParserTool.getDeviceFromListByIp(pf.getPingDest().getDestIpUrl(), deviceList);
			if( null == d ){
				pingFaultList.remove(i);
				i--;
				continue;
			}
			 
			// 执行ping操作，返回结果
			MonitorPingResult pr = PingTools.ping(pf.getPingDest().getDestIpUrl(), 2, 32, 1000);
			if (pr != null) {
				pr.setMonitorPingDest(pf.getPingDest());
				if (pr.getSuccessCount() <= 0) {
					// 无反应者增加计数器
					pf.increaseFaultCount();
				} else {
					// 有反应者计数器置0
					pf.clearCount();
				}
			}
		} // for end
		alerter.checkPingDeviceFault();
	}

	public Alerter getAlerter() {
		return alerter;
	}
	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
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
	
}
