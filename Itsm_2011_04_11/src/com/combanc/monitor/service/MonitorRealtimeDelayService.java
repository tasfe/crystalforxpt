package com.combanc.monitor.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorRealtimeDelayDAO;
import com.combanc.monitor.pojo.MonitorRealtimeDelay;

public class MonitorRealtimeDelayService extends BaseService<MonitorRealtimeDelay, Integer> {
	
	private MonitorRealtimeDelayDAO monitorRealtimeDelayDAO;
	
	/**批量插入**/
	public int[] batchInsert(List< MonitorRealtimeDelay> realtimeDelayList){
		return monitorRealtimeDelayDAO.batchInsert(realtimeDelayList);
		
	}
	
	/**topN**/
	public List<MonitorRealtimeDelay> findTopN(Integer topN){
		return monitorRealtimeDelayDAO.findTopN(topN);
	}
	
	public MonitorRealtimeDelayDAO getMonitorRealtimeDelayDAO() {
		return monitorRealtimeDelayDAO;
	}

	public void setMonitorRealtimeDelayDAO(
			MonitorRealtimeDelayDAO monitorRealtimeDelayDAO) {
		super.setDao(monitorRealtimeDelayDAO);
		this.monitorRealtimeDelayDAO = monitorRealtimeDelayDAO;
	}
	
	

}
