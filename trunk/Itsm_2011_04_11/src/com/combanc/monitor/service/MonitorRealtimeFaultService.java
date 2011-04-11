package com.combanc.monitor.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorRealtimeFaultDAO;
import com.combanc.monitor.pojo.MonitorRealtimeFault;

public class MonitorRealtimeFaultService extends BaseService<MonitorRealtimeFault, Integer> {
	
	private MonitorRealtimeFaultDAO monitorRealtimeFaultDAO;

	/**批量插入**/
	public int[] batchInsert(List< MonitorRealtimeFault> realtimeFaultList){
		return monitorRealtimeFaultDAO.batchInsert(realtimeFaultList);
		
	}
	/**topN**/
	public List<MonitorRealtimeFault> findTopN(Integer topN){
		return monitorRealtimeFaultDAO.findTopN(topN);
	}
	public MonitorRealtimeFaultDAO getMonitorRealtimeFaultDAO() {
		return monitorRealtimeFaultDAO;
	}

	public void setMonitorRealtimeFaultDAO(
			MonitorRealtimeFaultDAO monitorRealtimeFaultDAO) {
		super.setDao(monitorRealtimeFaultDAO);
		this.monitorRealtimeFaultDAO = monitorRealtimeFaultDAO;
	}
	
	

}
