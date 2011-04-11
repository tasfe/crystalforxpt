/**
 * Title:报警公用数据
 * Description: 以静态数据的形式存放报警用到的公用数据
 * Copyright: Copyright (c) 2010
 * Company: Combanc
 * @author 
 * @version 
 */
package com.combanc.monitor.alert.alertParser;

import java.util.ArrayList;
import java.util.List;

import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.alert.alertParser.abnormalCounter.CpuHight;
import com.combanc.monitor.alert.alertParser.abnormalCounter.DeviceFault;
import com.combanc.monitor.alert.alertParser.abnormalCounter.FlowHight;
import com.combanc.monitor.alert.alertParser.abnormalCounter.KeyInterfaceFault;
import com.combanc.monitor.alert.alertParser.abnormalCounter.PingFault;
import com.combanc.monitor.alert.alertParser.abnormalCounter.ProcessFault;
import com.combanc.monitor.alert.alertParser.abnormalCounter.ServiceFault;
import com.combanc.monitor.alert.alertParser.abnormalCounter.TempHight;
import com.combanc.monitor.alert.alertParser.abnormalCounter.UrlFault;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorPingDest;
import com.combanc.monitor.service.MonitorSystemParamService;

public class Alerter {

	/** 设备故障计数器列表 **/
	public static List<DeviceFault> deviceFaultList = new ArrayList<DeviceFault>();
	/** 服务无反应计数器列表 **/
	public static List<ServiceFault> serviceFaultList = new ArrayList<ServiceFault>();
	/** 设备Ping不通计数器列表 **/
	public static List<PingFault> pingDeviceFaultList = new ArrayList<PingFault>();
	/** CPU超阈值计数器列表 **/
	public static List<CpuHight> cpuHightList = new ArrayList<CpuHight>();
	/** 带宽占用超阈值计数器列表 **/
	public static List<FlowHight> flowHightList = new ArrayList<FlowHight>();
	/** 温度超阈值计数器列表 **/
	public static List<TempHight> tempHightList = new ArrayList<TempHight>();
	/** 服务器进程列表 **/
	public static List<ProcessFault> processFaultList = new ArrayList<ProcessFault>();
	/** 关键接口下线计数器列表 **/
	public static List<KeyInterfaceFault> keyInterfaceFaultList = new ArrayList<KeyInterfaceFault>();
	/** 计算机类报警 **/
	public static List<MonitorAlert> computerAlertList = new ArrayList<MonitorAlert>();
	/** ARP类报警 **/
	public static List<MonitorAlert> arpAlertList = new ArrayList<MonitorAlert>();
	/** URL监测报警 **/
	public static List<UrlFault> urlFaultList = new ArrayList<UrlFault>();
	/** Ping监测报警 **/
	public static List<PingFault> pingFaultList = new ArrayList<PingFault>();
	
	private MonitorSystemParamService monitorSystemParamService;
	
	private ArpAlertParser arpAlertParser;
	
	private ComputerAlertParser computerAlertParser;
	
	private CpuHightParser cpuHightParser;
	
	private DeviceFaultParser deviceFaultParser;
	
	private PingDeviceFaultParser pingDeviceFaultParser;
	
	private FlowHightParser flowHightParser;
	
	private KeyInterfaceFaultParser keyInterfaceFaultParser;
	
	private PingFaultParser pingFaultParser;
	
	private ProcessFaultParser processFaultParser;
	
	private ServiceFaultParser serviceFaultParser;
	
	private TempHightParser tempHightParser;
	
	private UrlFaultParser urlFaultParser;
	
	/** 检查指定的设备是否已经存在于“设备故障计数器列表”中**/
	public  boolean mergeDeviceFault(int deviceId) {
		for(DeviceFault df : deviceFaultList) {
			if( df.getDeviceId().equals(deviceId) ){
				return false;
			}
		}
		DeviceFault df = new DeviceFault(deviceId);
		deviceFaultList.add(df);
		return true;
	}
	
	/** 检查指定的服务是否已经存在于“服务异常计数器列表”中**/
	public  boolean mergeServiceFault(int deviceId, int servicePort, String serviceName) {
		for(ServiceFault sf : serviceFaultList) {
			if( sf.getDeviceId().equals(deviceId) 
					&& sf.getServicePort().equals(servicePort) ){
				return false;
			}
		}
		ServiceFault sf = new ServiceFault(deviceId, servicePort, serviceName);
		serviceFaultList.add(sf);
		return true;
	}
	/**检查指定的设备的温度是否超阈值，如果超阈值，是否已经存在于“设备温度超阈值计数器列表”中**/
	public  boolean mergeTempHight(int deviceId, long tempValue) {
		// 判断温度是否超阈值
			for(TempHight th : tempHightList) {
				if( th.getDeviceId().equals(deviceId) ){
					return false;
				}
			}
			TempHight th = new TempHight(deviceId);
			tempHightList.add(th);
			return true;
		
	}
	/** 检查指定的设备的CPU占用率是否超阈值，如果超阈值，是否已经存在于“设备CPU超阈值计数器列表”中**/
	public boolean mergeCpuHight(int deviceId, Float cpuValue) {
		 
		if( cpuValue > Float.parseFloat(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CPU_LIMEN).getValue())){
			for(CpuHight ch : cpuHightList) {
				if( ch.getDeviceId().equals(deviceId) ){
					return false;
				}
			}
			CpuHight ch = new CpuHight(deviceId);
			cpuHightList.add(ch);
			return true;
		} else {
			return false;
		}
	}
	
	/**检查指定的进程是否已经存在于“进程不存在计数器列表”中**/
	public boolean mergeProcessFault(int deviceId, String procName) {
		for(ProcessFault pf : processFaultList) {
			if( pf.getDeviceId().equals(deviceId) 
					&& pf.getProcName().equals(procName)){
				return false;
			}
		}
		ProcessFault pf = new ProcessFault(deviceId, procName);
		processFaultList.add(pf);
		return true;
	}
	
	/**检查指定的接口的带宽占用是否超阈值，如果超阈值，是否已经存在于“接口带宽占用超阈值计数器列表”中**/
	public boolean mergeFlowHight(int deviceId, int interface_, int bandRate) {
		// 判断带宽占用是否超阈值
		if( bandRate > 0) {
			String bandLimen=monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BAND_LIMEN).getValue();
			if(null!=bandLimen&&!"".equals(bandLimen)&&	bandRate > Float.parseFloat(bandLimen)){
				for(FlowHight fh : flowHightList) {
					if( fh.getDeviceId().equals(deviceId) && interface_ == fh.getInterface_()){
						return false;
					}
				}
				FlowHight fh = new FlowHight(deviceId, interface_);
				flowHightList.add(fh);
				return true;
			} else {
				return false;
			}
		} else 
			return false;
	}
	/** 检查指定的接口是否已经存在于“接口下线计数器列表”中 **/
	public boolean mergeKeyInterfaceFault(int deviceId, String interfaceNum, 
			String interfaceDesc, String interfaceNote, int faultType) {
		for(KeyInterfaceFault kif : keyInterfaceFaultList) {
			if( kif.getDeviceId().equals(deviceId) 
					&& kif.getInterfaceNum().equals(interfaceNum)){
				return false;
			}
		}
		KeyInterfaceFault kif = new KeyInterfaceFault(deviceId, 
				interfaceNum, interfaceDesc, interfaceNote, faultType);
		keyInterfaceFaultList.add(kif);
		return true;
	}
	/** 检查指定的Url是否已经存在于“Url异常计数器列表”中 **/
	public boolean mergeUrlFault(int urlId, String sIp, String sMsg) {
		for (UrlFault uf : urlFaultList) {
			if (uf.getUrlId()== urlId){
				return false;
			}
		}
		UrlFault uf = new UrlFault(urlId);
		uf.setsMsg(sMsg);
		uf.setsIp(sIp);
		urlFaultList.add(uf);
		return true;
	}
	/** 检查指定的PingDest是否已经存在于“Ping不通计数器列表”中 **/
	public boolean mergePingDeviceFault(MonitorPingDest pingDest) {
		if ( null == pingDest ) {
			return false;
		}
		for (PingFault pf : pingDeviceFaultList) {
			if ( null == pf.getPingDest())
				continue;
			if (pf.getPingDest().getDestIpUrl().equals(pingDest.getDestIpUrl())) {
				return false;
			}
		}
		if (pingDeviceFaultList.size() <= 50) {	// pingFaultList过长会导致小循环线程过慢
			PingFault pf = new PingFault(pingDest);
			pingDeviceFaultList.add(pf);
		}
		return true;
	}
	/** 检查指定的PingDest是否已经存在于“Ping不通计数器列表”中 **/
	public boolean mergePingFault(MonitorPingDest pingDest) {
		if ( null == pingDest ) {
			return false;
		}
		for (PingFault pf : pingFaultList) {
			if ( null == pf.getPingDest())
				continue;
			if (pf.getPingDest().getDestIpUrl().equals(pingDest.getDestIpUrl())) {
				return false;
			}
		}
		if (pingFaultList.size() <= 50) {	// pingFaultList过长会导致小循环线程过慢
			PingFault pf = new PingFault(pingDest);
			pingFaultList.add(pf);
		}
		return true;
	}
	
	/** ARP类报警 **/
	public void checkArpAlert() {
		arpAlertParser.setArpAlertList(arpAlertList);
		arpAlertParser.checkAlert();		
	}
	
	/** 计算机类报警 **/
	public void checkComputerAlert() {
		computerAlertParser.setComputerAlertList(computerAlertList);
		computerAlertParser.checkAlert();		
	}
	
	/** 检查CPU超阈值计数器，形成报警 **/
	public void checkCpuHight() {
		cpuHightParser.setCpuHightList(cpuHightList);
		cpuHightParser.checkAlert();	
	}
	
	/** 检查设备故障计数器，形成报警 **/
	public void checkDeviceFault() {
		//DeviceFaultParser deviceFaultParser = new DeviceFaultParser(deviceFaultList);
		deviceFaultParser.setDeviceFaultList(deviceFaultList);
		deviceFaultParser.checkAlert();
	}
	/** Ping监测类报警 **/
	public void checkPingDeviceFault() {
		pingDeviceFaultParser.setPingFaultList(pingDeviceFaultList);
		pingDeviceFaultParser.checkAlert();		
	}
	
	/** 检查带宽占用超阈值计数器，形成报警 **/
	public void checkFlowHight(){
		flowHightParser.setFlowHightList(flowHightList);
		flowHightParser.checkAlert();
	}
	
	/** 检查接口异常的计数器，形成报警 **/
	public void checkKeyInterfaceFault() {
		keyInterfaceFaultParser.setKeyInterfaceFaultList(keyInterfaceFaultList);
		keyInterfaceFaultParser.checkAlert();		
	}
	
	/** Ping监测类报警 **/
	public void checkPingFault() {
		pingFaultParser.setPingFaultList(pingFaultList);
		pingFaultParser.checkAlert();		
	}
	
	/** 检查服务器进程不存在的计数器，形成报警 **/
	public void checkProcessFault() {
		processFaultParser.setProcessFaultList(processFaultList);
		processFaultParser.checkAlert();		
	}
	
	/** 检查服务无响应计数器，形成报警 **/
	public void checkServiceFault() {
		serviceFaultParser.setServiceFaultList(serviceFaultList);
		serviceFaultParser.checkAlert();		
	}
	/** 检查温度超阈值计数器，形成报警 **/
	public void checkTempHight() {
		tempHightParser.setTempHightList(tempHightList);
		tempHightParser.checkAlert();		
	}
	
	/** URL监测类报警 **/
	public void checkUrlFault() {
		urlFaultParser.setUrlFaultList(urlFaultList);
		urlFaultParser.checkAlert();		
	}
	
	
	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public CpuHightParser getCpuHightParser() {
		return cpuHightParser;
	}

	public void setCpuHightParser(CpuHightParser cpuHightParser) {
		this.cpuHightParser = cpuHightParser;
	}

	public DeviceFaultParser getDeviceFaultParser() {
		return deviceFaultParser;
	}

	public void setDeviceFaultParser(DeviceFaultParser deviceFaultParser) {
		this.deviceFaultParser = deviceFaultParser;
	}

	public FlowHightParser getFlowHightParser() {
		return flowHightParser;
	}

	public void setFlowHightParser(FlowHightParser flowHightParser) {
		this.flowHightParser = flowHightParser;
	}

	public KeyInterfaceFaultParser getKeyInterfaceFaultParser() {
		return keyInterfaceFaultParser;
	}

	public void setKeyInterfaceFaultParser(
			KeyInterfaceFaultParser keyInterfaceFaultParser) {
		this.keyInterfaceFaultParser = keyInterfaceFaultParser;
	}

	public PingFaultParser getPingFaultParser() {
		return pingFaultParser;
	}

	public void setPingFaultParser(PingFaultParser pingFaultParser) {
		this.pingFaultParser = pingFaultParser;
	}

	public ProcessFaultParser getProcessFaultParser() {
		return processFaultParser;
	}

	public void setProcessFaultParser(ProcessFaultParser processFaultParser) {
		this.processFaultParser = processFaultParser;
	}

	public ServiceFaultParser getServiceFaultParser() {
		return serviceFaultParser;
	}

	public void setServiceFaultParser(ServiceFaultParser serviceFaultParser) {
		this.serviceFaultParser = serviceFaultParser;
	}

	public TempHightParser getTempHightParser() {
		return tempHightParser;
	}

	public void setTempHightParser(TempHightParser tempHightParser) {
		this.tempHightParser = tempHightParser;
	}

	public UrlFaultParser getUrlFaultParser() {
		return urlFaultParser;
	}

	public void setUrlFaultParser(UrlFaultParser urlFaultParser) {
		this.urlFaultParser = urlFaultParser;
	}

	public ComputerAlertParser getComputerAlertParser() {
		return computerAlertParser;
	}

	public void setComputerAlertParser(ComputerAlertParser computerAlertParser) {
		this.computerAlertParser = computerAlertParser;
	}

	public static List<MonitorAlert> getArpAlertList() {
		return arpAlertList;
	}

	public static void setArpAlertList(List<MonitorAlert> arpAlertList) {
		Alerter.arpAlertList = arpAlertList;
	}

	public ArpAlertParser getArpAlertParser() {
		return arpAlertParser;
	}

	public void setArpAlertParser(ArpAlertParser arpAlertParser) {
		this.arpAlertParser = arpAlertParser;
	}

	public PingDeviceFaultParser getPingDeviceFaultParser() {
		return pingDeviceFaultParser;
	}

	public void setPingDeviceFaultParser(PingDeviceFaultParser pingDeviceFaultParser) {
		this.pingDeviceFaultParser = pingDeviceFaultParser;
	}
	
	
}
