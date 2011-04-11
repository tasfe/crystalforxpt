package com.combanc.monitor.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorRealtimePortflowDAO;
import com.combanc.monitor.pojo.MonitorRealtimePortflow;

public class MonitorRealtimePortflowService extends BaseService<MonitorRealtimePortflow, Integer> {
	
	private MonitorRealtimePortflowDAO monitorRealtimePortflowDAO;

	/**批量插入**/
	public int[] batchInsert(List< MonitorRealtimePortflow> realtimePortflowList){
		return monitorRealtimePortflowDAO.batchInsert(realtimePortflowList);
		
	}
	/**批量更新**/
	public int[] batchUpdate(List< MonitorRealtimePortflow> realtimePortflowList){
		return monitorRealtimePortflowDAO.batchUpdate(realtimePortflowList);
		
	}
	/**topN type=1:互联端口，type=0 用户端口**/
	public List<MonitorRealtimePortflow> findTopN(Integer subnetId,Integer topN,Integer type){
		return monitorRealtimePortflowDAO.findTopN(subnetId,topN,type);
	}
	public MonitorRealtimePortflowDAO getMonitorRealtimePortflowDAO() {
		return monitorRealtimePortflowDAO;
	}

	public void setMonitorRealtimePortflowDAO(
			MonitorRealtimePortflowDAO monitorRealtimePortflowDAO) {
		super.setDao(monitorRealtimePortflowDAO);
		this.monitorRealtimePortflowDAO = monitorRealtimePortflowDAO;
	}
	

}
