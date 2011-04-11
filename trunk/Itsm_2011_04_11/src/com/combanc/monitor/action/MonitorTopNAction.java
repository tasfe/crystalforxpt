package com.combanc.monitor.action;

import java.util.Iterator;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorRealtimeCpu;
import com.combanc.monitor.pojo.MonitorRealtimeDelay;
import com.combanc.monitor.pojo.MonitorRealtimeFault;
import com.combanc.monitor.pojo.MonitorRealtimePortflow;
import com.combanc.monitor.pojo.MonitorRealtimeUse;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorRealtimeCpuService;
import com.combanc.monitor.service.MonitorRealtimeDelayService;
import com.combanc.monitor.service.MonitorRealtimeFaultService;
import com.combanc.monitor.service.MonitorRealtimePortflowService;
import com.combanc.monitor.service.MonitorRealtimeUseService;
import com.combanc.monitor.service.MonitorSystemParamService;

public class MonitorTopNAction extends BaseActionSupport implements ServletRequestAware{
	
	private Integer subnetId;
	
	private String topN;
	/**故障 TOP N**/
	private List<MonitorRealtimeFault> realtimeFaultList;
	/**响应 TOP N**/
	private List<MonitorRealtimeDelay> realtimeDelayList;
	/**可用 TOP N**/
	private List<MonitorRealtimeUse> realtimeUseList;
	/**CPU TOP N**/
	private List<MonitorRealtimeCpu> realtimeCpuList;
	/**互联端口流量 TOP N**/
	private List<MonitorRealtimePortflow> realtimeLinkPortFlowList;
	/**用户端口流量 TOP N**/
	private List<MonitorRealtimePortflow> realtimeUsePortFlowList;
	
	private MonitorRealtimeFaultService monitorRealtimeFaultService;
	private MonitorRealtimeDelayService monitorRealtimeDelayService;
	private MonitorRealtimeUseService monitorRealtimeUseService;
	private MonitorRealtimeCpuService monitorRealtimeCpuService;
	private MonitorRealtimePortflowService monitorRealtimePortflowService;
	private MonitorSystemParamService monitorSystemParamService;
	private MonitorLinkportService monitorLinkportService;
	
	public MonitorTopNAction(){
		 
	}
	/**主页浏览所有**/
	public String listAll(){
		try{
			this.topN=monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_TOP_N).getValue();
			Integer intTopN = Integer.parseInt(this.topN);
			realtimeFaultList = monitorRealtimeFaultService.findTopN(intTopN);
			realtimeDelayList = monitorRealtimeDelayService.findTopN(intTopN);
			realtimeUseList = monitorRealtimeUseService.findTopN(intTopN);
			realtimeCpuList = monitorRealtimeCpuService.findTopN(intTopN);
			List<MonitorLinkport> linkportList=monitorLinkportService.findEffectiveLinkports(null);
			realtimeLinkPortFlowList = monitorRealtimePortflowService.findTopN(null,intTopN*2, 1);
			Iterator it=realtimeLinkPortFlowList.iterator();
			int n = 0;
			while( it.hasNext() ){
				MonitorRealtimePortflow model= (MonitorRealtimePortflow) it.next();
				boolean isEffective=false;
				for(MonitorLinkport linkport: linkportList){
					if(model.getDeviceIp().equals(linkport.getIp())
							&&model.getInterface_().equals(linkport.getInterface_())){
						isEffective=true;
						break;
					}
				}
				if(!isEffective||n>=intTopN)
					it.remove();
				else
					n++;
				
			}
				
			 
			
			realtimeUsePortFlowList = monitorRealtimePortflowService.findTopN(null,intTopN, 0);
		} catch(NumberFormatException nfe){
			this.addActionError("top N value is not right!");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String query(){
		
		return SUCCESS;
	}
	public List<MonitorRealtimeFault> getRealtimeFaultList() {
		return realtimeFaultList;
	}

	public void setRealtimeFaultList(List<MonitorRealtimeFault> realtimeFaultList) {
		this.realtimeFaultList = realtimeFaultList;
	}

	public List<MonitorRealtimeDelay> getRealtimeDelayList() {
		return realtimeDelayList;
	}

	public void setRealtimeDelayList(List<MonitorRealtimeDelay> realtimeDelayList) {
		this.realtimeDelayList = realtimeDelayList;
	}

	public List<MonitorRealtimeUse> getRealtimeUseList() {
		return realtimeUseList;
	}

	public void setRealtimeUseList(List<MonitorRealtimeUse> realtimeUseList) {
		this.realtimeUseList = realtimeUseList;
	}

	public List<MonitorRealtimeCpu> getRealtimeCpuList() {
		return realtimeCpuList;
	}

	public void setRealtimeCpuList(List<MonitorRealtimeCpu> realtimeCpuList) {
		this.realtimeCpuList = realtimeCpuList;
	}

	public List<MonitorRealtimePortflow> getRealtimeLinkPortFlowList() {
		return realtimeLinkPortFlowList;
	}

	public void setRealtimeLinkPortFlowList(
			List<MonitorRealtimePortflow> realtimeLinkPortFlowList) {
		this.realtimeLinkPortFlowList = realtimeLinkPortFlowList;
	}

	public List<MonitorRealtimePortflow> getRealtimeUsePortFlowList() {
		return realtimeUsePortFlowList;
	}

	public void setRealtimeUsePortFlowList(
			List<MonitorRealtimePortflow> realtimeUsePortFlowList) {
		this.realtimeUsePortFlowList = realtimeUsePortFlowList;
	}

	public MonitorRealtimeFaultService getMonitorRealtimeFaultService() {
		return monitorRealtimeFaultService;
	}

	public void setMonitorRealtimeFaultService(
			MonitorRealtimeFaultService monitorRealtimeFaultService) {
		this.monitorRealtimeFaultService = monitorRealtimeFaultService;
	}

	public MonitorRealtimeDelayService getMonitorRealtimeDelayService() {
		return monitorRealtimeDelayService;
	}

	public void setMonitorRealtimeDelayService(
			MonitorRealtimeDelayService monitorRealtimeDelayService) {
		this.monitorRealtimeDelayService = monitorRealtimeDelayService;
	}

	public MonitorRealtimeUseService getMonitorRealtimeUseService() {
		return monitorRealtimeUseService;
	}

	public void setMonitorRealtimeUseService(
			MonitorRealtimeUseService monitorRealtimeUseService) {
		this.monitorRealtimeUseService = monitorRealtimeUseService;
	}

	public MonitorRealtimeCpuService getMonitorRealtimeCpuService() {
		return monitorRealtimeCpuService;
	}

	public void setMonitorRealtimeCpuService(
			MonitorRealtimeCpuService monitorRealtimeCpuService) {
		this.monitorRealtimeCpuService = monitorRealtimeCpuService;
	}

	public MonitorRealtimePortflowService getMonitorRealtimePortflowService() {
		return monitorRealtimePortflowService;
	}

	public void setMonitorRealtimePortflowService(
			MonitorRealtimePortflowService monitorRealtimePortflowService) {
		this.monitorRealtimePortflowService = monitorRealtimePortflowService;
	}
	public String getTopN() {
		return topN;
	}
	public void setTopN(String topN) {
		this.topN = topN;
	}
	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}
	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}
	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}
	public void setMonitorLinkportService(
			MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}
	public Integer getSubnetId() {
		return subnetId;
	}
	public void setSubnetId(Integer subnetId) {
		this.subnetId = subnetId;
	}
	
	
}
