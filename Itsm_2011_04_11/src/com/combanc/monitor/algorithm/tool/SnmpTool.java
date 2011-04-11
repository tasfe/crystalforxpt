package com.combanc.monitor.algorithm.tool;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.monitor.AppContextUtil;
import com.combanc.monitor.constants.V3Constants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorV3Params;
import com.combanc.monitor.pojo.MonitorV3SecurityLevel;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.util.ApplicationContextUtil;

public class SnmpTool {

	SnmpTarget snmpTarget;
	MonitorDeviceService deviceService;
	
	public SnmpTool(SnmpTarget snmpTarget) {
		deviceService = (MonitorDeviceService) ApplicationContextUtil.getContext().getBean("MonitorDeviceService");
		this.snmpTarget = snmpTarget;
	}
	
	public SnmpTarget initSnmpTarget(String ip) {
//		System.out.println("USMTable.size ==   " + snmpTarget.getUSMTable().getEntries("172.16.0.1", 161).size());
		snmpTarget.setSnmpVersion(SnmpTarget.VERSION1);
		MonitorDevice device = deviceService.findDeviceByIp(ip);
		if(device == null)
			return snmpTarget;
		if(null == device.getMonitorV3Params()) {
			snmpTarget.setTargetHost(device.getIp());
			snmpTarget.setCommunity(device.getReadCommunity());
			String writeComm = device.getWriteCommunity();
			if(!(writeComm == null || "".equals(writeComm)))
				snmpTarget.setWriteCommunity(writeComm);
			return snmpTarget;
		}else{
			// snmpTarget.getUSMTable().removeAllEntries();
			snmpTarget.setTargetHost(device.getIp());
			MonitorV3Params v3Params = device.getMonitorV3Params();
			String username = v3Params.getUserName();
			MonitorV3SecurityLevel level = v3Params.getMonitorV3SecurityLevel();
			if(null == username  || "".equals(username)) {
				return snmpTarget;
			}
			snmpTarget.setSnmpVersion(SnmpTarget.VERSION3);
			snmpTarget.setPrincipal(username);
			snmpTarget.setSecurityLevel(level.getLevel());
			if (V3Constants.SECURITY_LEV1.equals(level.getName())
					|| V3Constants.SECURITY_LEV3.equals(level.getName())) {
				snmpTarget.setAuthProtocol(v3Params.getMonitorV3Auth().getProtocol());
				snmpTarget.setAuthPassword(v3Params.getAuthPassword());
			}
			if (V3Constants.SECURITY_LEV3.equals(level.getName())) {
				snmpTarget.setPrivProtocol(v3Params.getMonitorV3Priv().getProtocol());
//				snmpTarget.setPrivProtocol(SnmpTarget.CFB_AES_128);
				snmpTarget.setPrivPassword(v3Params.getPrivPassword());
			}
			// system.sysObjectID
			String OID = ".1.3.6.1.2.1.1.1.0";

			snmpTarget.create_v3_tables();
//			System.out.println("111   " + snmpTarget.getPrincipal().getBytes() + "  " +  snmpTarget.getEngineID());
//			System.out.println("222   " + snmpTarget.getUSMTable().getEntry(snmpTarget.getPrincipal().getBytes(), snmpTarget.getEngineID()));
//			System.out.println("222   " + new String(((USMUserEntry)snmpTarget.getUSMTable().getEntries("172.16.0.1", 161).elementAt(0)).getUserName()));
			// snmpTarget.releaseResources();
			// snmpTarget.removeLogClient();
			// System.out.println("333   " + snmpTarget.getPrincipal());
//			snmpTarget.getUSMTable().removeAllEntries();
			
		}
		return snmpTarget;
	}
	public SnmpTarget getSnmpTarget() {
		return snmpTarget;
	}
	public void setSnmpTarget(SnmpTarget snmpTarget) {
		this.snmpTarget = snmpTarget;
	}
	public MonitorDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(MonitorDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	
}
