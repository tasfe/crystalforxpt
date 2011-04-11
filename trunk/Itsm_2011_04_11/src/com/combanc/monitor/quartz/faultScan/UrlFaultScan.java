package com.combanc.monitor.quartz.faultScan;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.TempHight;
import com.combanc.monitor.alert.alertParser.abnormalCounter.UrlFault;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorUrlResponse;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorUrlResponseService;
import com.combanc.monitor.vo.urlMonitor.model.MonitorResult;

/**
 * <p>
 * Title:URL监测类报警扫描
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
public class UrlFaultScan extends BaseScan  {
	
	private static final Log log = LogFactory.getLog(TempHightScan.class);
	/** 设备列表 **/
	private List<MonitorDevice> serverList = new ArrayList<MonitorDevice>();
	// URL监控列表
	private List<MonitorUrlResponse> urlList = null;
	
	private MonitorDeviceService monitorDeviceService;
	private MonitorUrlResponseService monitorUrlResponseService;
	private DeviceSnmpQuery deviceSnmpQuery;
	private Alerter alerter;
	
	public UrlFaultScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerUrlFaultScanTask";
	}
	/**初始化**/
	@SuppressWarnings("unchecked")
	public void init(){
		serverList = monitorDeviceService.findByDeviceType(MainConstants.DEVICE_SERVER);
		urlList = monitorUrlResponseService.getAll();
	}
	public void startScan(){
		System.out.println("URL监测类报警扫描开始");
		init();
		List<UrlFault> list = Alerter.urlFaultList;
		if ( null == serverList || serverList.isEmpty() || null == list || list.isEmpty())
			return;
		
		String ret;
		synchronized (Alerter.urlFaultList) {
			// 搜集存在的服务器的IP，汇成IP列表
			List<String> ipList = new ArrayList<String>();
			for (MonitorDevice dv : serverList) {
				if (!ipList.contains(dv.getIp())) {
					ipList.add(dv.getIp());
				}
			}
			// 检查URL列表，所属服务器不存在的，从URL列表中删除
			for (int i=0; i<list.size(); i++) {
				UrlFault uf = list.get(i);
				if (!ipList.contains(uf.getsIp())) {
					list.remove(i);
					i--;
				}
			}
			if (  null == list || list.isEmpty())
				return;
			for( int i=0; i<list.size(); i++ ){
				UrlFault uf = list.get(i);

				// 如果查不到，说明URL监测项已被删除，把异常列表中的该记录也清除
				MonitorUrlResponse hur = ParserTool.getUrlFromList(uf.getUrlId(), urlList);
				if(hur == null){
					list.remove(i);
					i--;
					continue;
				}
				MonitorResult result = monitorUrlResponseService.testUrl(hur, false);
				if (result != null) {
					hur.setFlushTime(new Timestamp(System.currentTimeMillis()));
					hur.setStateCode(result.getState());
				} else {
					hur.setFlushTime(new Timestamp(System.currentTimeMillis()));
					hur.setStateCode(-2);
				}
				monitorUrlResponseService.update(hur);
				if (hur.getStateCode()<= 0) {
					// 无反应者增加计数器
					uf.IncreaseFaultCount();
				} else {
					// 有反应者计数器置0
					uf.clearCount();
				}
			}// for
			alerter.checkUrlFault();
			
		}
		
		System.out.println("URL列表检查完毕检查完毕.");
	}
	
	public List<MonitorDevice> getServerList() {
		return serverList;
	}
	public void setServerList(List<MonitorDevice> serverList) {
		this.serverList = serverList;
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
	public List<MonitorUrlResponse> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<MonitorUrlResponse> urlList) {
		this.urlList = urlList;
	}
	public MonitorUrlResponseService getMonitorUrlResponseService() {
		return monitorUrlResponseService;
	}
	public void setMonitorUrlResponseService(
			MonitorUrlResponseService monitorUrlResponseService) {
		this.monitorUrlResponseService = monitorUrlResponseService;
	}
	
	
}
