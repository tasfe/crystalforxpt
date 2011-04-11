package com.combanc.monitor.quartz.faultScan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.ServiceFault;
import com.combanc.monitor.alert.alertParser.abnormalCounter.TempHight;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorSystemParamService;

/**
 * <p>
 * Title:温度超阈值报警扫描
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
public class TempHightScan extends BaseScan {
	
	private static final Log log = LogFactory.getLog(TempHightScan.class);
	/** 设备列表 **/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	
	private MonitorDeviceService monitorDeviceService;
	private DeviceSnmpQuery deviceSnmpQuery;
	private Alerter alerter;
	
	public TempHightScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerTempHightScanTask";
	}
	
	private int tempLimen = 25;// AKCP温度阈值（度）
	/**初始化**/
	public void init(){
		deviceList=monitorDeviceService.findAll();
		MonitorSystemParam   mTempLimen = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_TEMP_LIMEN);
		if( null != mTempLimen && "".equals(mTempLimen.getValue())){
			try{
				tempLimen = Integer.parseInt(mTempLimen.getValue());
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
				
			}
			
		}
	}
	public void startScan(){
		System.out.println("温度超阈值报警扫描开始.");
		init();
		List<TempHight> list = Alerter.tempHightList;
		if ( null == list || list.isEmpty())
			return;
		String ret;
		synchronized (Alerter.tempHightList) {
			for (int i = 0; i < list.size(); i++) {
				TempHight th = list.get(i);

				// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
				MonitorDevice dv = ParserTool.getDeviceFromList(th.getDeviceId(), deviceList);
				if (dv == null) {
					list.remove(i);
					i--;
					continue;
				}
//				if (!MainFrame.subnetList.contains(dv.getSubnet().getSubnetName())) {
//					continue;
//				}
				
				//MainFrame.statusBar.setText("检查温度：" + d.getIp() + "...");
				
				// 获取温度
				ret = deviceSnmpQuery.getVendorOID(dv);
				// 维护温度超阈值计数器
				if (ret.equals("3854")) {
					long temp;
					temp = deviceSnmpQuery.getAkcpTemp(dv);
					if ((temp != -1) && temp > tempLimen) {
						// 如果超过，增加计数器
						th.IncreaseFaultCount();
						th.setCurrTemp((int)temp);
					} else {
						// 如果没超过，计数器置0
						th.clearCount();
						th.setCurrTemp((int)temp);
					}
				}
			}// for
			alerter.checkTempHight();
		}
		System.out.println("温度超阈值计数器列表检查完毕.");
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
	
	public int getTempLimen() {
		return tempLimen;
	}
	public void setTempLimen(int tempLimen) {
		this.tempLimen = tempLimen;
	}
	
	
	
	
}
