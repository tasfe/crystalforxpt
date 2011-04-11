package com.combanc.monitor.quartz.faultScan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.ProcessFault;
import com.combanc.monitor.alert.alertParser.abnormalCounter.ServiceFault;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;

/**
 * <p>
 * Title:服务无响应报警扫描
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
public class ServiceFaultScan extends BaseScan{

	private static final Log log = LogFactory.getLog(ServiceFaultScan.class);
	/** 设备列表 **/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	
	private MonitorDeviceService monitorDeviceService;
	private Alerter alerter;
	
	public ServiceFaultScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerServiceFaultScanTask";
	}
	
	/**初始化**/
	public void init(){
		deviceList=monitorDeviceService.findAll();
		 
	}
	public void scan(){
		System.out.println("服务器进程报警扫描开始");
		init();
	
		List<ServiceFault> list = Alerter.serviceFaultList;
		if ( null == list || list.isEmpty())
			return;
		String ret;
		synchronized (Alerter.serviceFaultList) {
			for (int i = 0; i < list.size(); i++) {
				ServiceFault sf = list.get(i);

				// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
				MonitorDevice d = ParserTool.getDeviceFromList(sf.getDeviceId(), deviceList);
				if (d == null) {
					list.remove(i);
					i--;
					continue;
				}
//				if (!MainFrame.subnetList.contains(d.getSubnet().getSubnetName())) {
//					continue;
//				}
				//MainFrame.statusBar.setText("检查服务：" + d.getIp() + "...");
				ret = Tools.tcpPing(d.getIp() + "@" + sf.getServicePort());
				if (ret == null || ret.equals("超时")) {
					// 无反应者增加计数器
					sf.IncreaseFaultCount();
				} else {
					// 有反应者计数器置0
					sf.clearCount();
				}
			}// for
			alerter.checkServiceFault();
		}// synchronized  end 
		System.out.println("服务故障列表检查完毕.");
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
	public Alerter getAlerter() {
		return alerter;
	}
	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}
	
	
}
