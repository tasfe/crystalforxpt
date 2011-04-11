package com.combanc.monitor.quartz.faultScan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.DeviceFault;
import com.combanc.monitor.alert.alertParser.abnormalCounter.ProcessFault;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.HostSnmpQuery;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;

/**
 * <p>
 * Title:服务器进程报警扫描
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
public class ProcessFaultScan extends BaseScan{
	
	private static final Log log = LogFactory.getLog(ProcessFaultScan.class);
	/** 设备列表 **/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	/** 内存中存储的procNameList，缓存 **/
	List<String> procNameList = new ArrayList<String>();
	/** 内存中存储的procNameList对应的服务器的id **/
	int currentDevice = -1;
	
	private MonitorDeviceService monitorDeviceService;
	
	private DeviceSnmpQuery deviceSnmpQuery;
	private HostSnmpQuery hostSnmpQuery;
	
	private Alerter alerter;
	
	public ProcessFaultScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerProcessFaultScanTask";
	}
	/**初始化**/
	public void init(){
		deviceList=monitorDeviceService.findAll();
		 
	}
	public void startScan(){
		System.out.println("服务器进程报警扫描开始");
		init();
	
		List<ProcessFault> processFaultList = Alerter.processFaultList;
		if ( null == processFaultList || processFaultList.isEmpty())
			return;
		String ret;
		synchronized (Alerter.processFaultList) {
			for (int i=0; i<processFaultList.size(); i++) {
				ProcessFault pf = processFaultList.get(i);
				if (!pf.isbMonitor())	// 是否处于被监控状态
					continue;
				int deviceId = pf.getDeviceId();
				// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
				MonitorDevice dev = ParserTool.getDeviceFromList(deviceId, deviceList);
				if( null == dev || null == pf.getProcName()|| "".equals(pf.getProcName())){
					processFaultList.remove(i);
					i--;
					continue;
				}
				ret = deviceSnmpQuery.getVendorOID(dev);
				if(ret == null || "超时".equals(ret))
					continue;
				if(!isProcessExist(dev, pf.getProcName())) {
					// 只有当服务器通，且能读到进程表，但进程表中不存在此进程时增加计数器
					pf.IncreaseFaultCount();
					System.out.println("进程不存在" + dev.getIp() + "  " + pf.getProcName());
				} else {
					// 计数器清零
					pf.clearCount();
				}
			}// for
			alerter.checkProcessFault();
		}// synchronized (MainFrame.alerter.processFaultList) {
	}

	/**
	 * 检查进程是否存在，
	 * 只有当服务器通，且能读到进程表，但进程表中不存在此进程时返回false
	 * @param dev
	 * @param name
	 * @return
	 */
	private boolean isProcessExist(MonitorDevice dev, String name) {
		String ret = "";
		// 当第一次查询是否存在时，procNameList为空，进程初始化
		// 之后因为监测进程是按照服务器的id排序
		// 因此直到现有dev的id和内存中保存的currentDevice不同时才再次读取
		if(procNameList == null || procNameList.isEmpty() || !dev.getId().equals(currentDevice)) {
			String str = "读取 " + dev.getIp() + "的进程表...";
			System.out.println(str);
			//MainFrame.statusBar.setText(str);
			ret = hostSnmpQuery.getProcessName(dev, procNameList);
			// 读取“超时”、“表空”、“不支持该特性”时返回true
			if(!"OK".equals(ret))
				return true;
			else {
				currentDevice = dev.getId();
			}
		}
		// 读取结果的procNameList中包含进程名称时返回true
		for(String s : procNameList) {
			if(name.equals(s))
				return true;
		}
		return false;
	}
	
	public List<MonitorDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<MonitorDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public List<String> getProcNameList() {
		return procNameList;
	}

	public void setProcNameList(List<String> procNameList) {
		this.procNameList = procNameList;
	}

	public int getCurrentDevice() {
		return currentDevice;
	}

	public void setCurrentDevice(int currentDevice) {
		this.currentDevice = currentDevice;
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

	public HostSnmpQuery getHostSnmpQuery() {
		return hostSnmpQuery;
	}

	public void setHostSnmpQuery(HostSnmpQuery hostSnmpQuery) {
		this.hostSnmpQuery = hostSnmpQuery;
	}

	public Alerter getAlerter() {
		return alerter;
	}

	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}
	
	
	
}
