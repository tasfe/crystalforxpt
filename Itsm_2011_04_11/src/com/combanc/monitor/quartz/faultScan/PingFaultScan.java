package com.combanc.monitor.quartz.faultScan;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.CpuHight;
import com.combanc.monitor.alert.alertParser.abnormalCounter.PingFault;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorPingDest;
import com.combanc.monitor.pojo.MonitorPingResult;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorPingDestService;
import com.combanc.monitor.util.PingTools;

public class PingFaultScan extends BaseScan{

	private static final Log log = LogFactory.getLog(PingFaultScan.class);
	// SNMP主机表
	private List<MonitorPingDest> pingDestList = null;
	
	private MonitorPingDestService monitorPingDestService;
	
	private Alerter alerter;
	
	public PingFaultScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerPingFaultScanTask";
	}
	
	/**初始化**/
	public void init(){
		pingDestList = monitorPingDestService.getAll();
		 
	}
	public void startScan(){
		System.out.println("start ping scan ......");
		init();
		if (pingDestList == null || pingDestList.isEmpty()) 
			return;
		List<PingFault> pingFaultList = Alerter.pingFaultList;
		if ( null == pingFaultList|| pingFaultList.isEmpty())
			return;
		for( int i=0; i<pingFaultList.size(); i++ ){
			PingFault pf = pingFaultList.get(i);
			// 如果查不到，说明Ping监测项已被删除，把异常列表中的该记录也清除
			MonitorPingDest thePd = ParserTool.getPingDestFromList(pf.getPingDest(), pingDestList);
			if(thePd == null || !thePd.getIsRun()){
				pingFaultList.remove(i);
				i--;
				continue;
			}
			// 执行ping操作，返回结果
			MonitorPingResult pr = PingTools.ping(thePd.getDestIpUrl(), 2, 32, 1000);
			if (pr != null) {
				pr.setMonitorPingDest(thePd);
				if (pr.getSuccessCount() <= 0) {
					// 无反应者增加计数器
					pf.increaseFaultCount();
				} else {
					// 有反应者计数器置0
					pf.clearCount();
				}
			}
		} // for end
		alerter.checkPingFault();
	}
	public List<MonitorPingDest> getPingDestList() {
		return pingDestList;
	}
	public void setPingDestList(List<MonitorPingDest> pingDestList) {
		this.pingDestList = pingDestList;
	}
	
	public MonitorPingDestService getMonitorPingDestService() {
		return monitorPingDestService;
	}
	public void setMonitorPingDestService(
			MonitorPingDestService monitorPingDestService) {
		this.monitorPingDestService = monitorPingDestService;
	}
	public Alerter getAlerter() {
		return alerter;
	}
	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}
	
	
}
