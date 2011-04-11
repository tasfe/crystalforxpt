package com.combanc.monitor.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorRealtimeCpuDAO;
import com.combanc.monitor.pojo.MonitorRealtimeCpu;
import com.combanc.monitor.pojo.MonitorRealtimeDelay;
import com.combanc.monitor.pojo.MonitorRealtimeFault;

public class MonitorRealtimeCpuService extends BaseService<MonitorRealtimeCpu, Integer> {
	
	private MonitorRealtimeCpuDAO monitorRealtimeCpuDAO;
	
	/**批量插入**/
	public int[] batchInsert(List< MonitorRealtimeCpu> realtimeCpuList){
		return monitorRealtimeCpuDAO.batchInsert(realtimeCpuList);
		
	}
	/**topN**/
	public List<MonitorRealtimeCpu> findTopN(Integer topN){
		return monitorRealtimeCpuDAO.findTopN(topN);
	}
	/**topN**/
	public List<MonitorRealtimeCpu> findTopN(Integer subnetId,Integer topN){
		return monitorRealtimeCpuDAO.findTopN(subnetId,topN);
	}
	public MonitorRealtimeCpuDAO getMonitorRealtimeCpuDAO() {
		return monitorRealtimeCpuDAO;
	}

	public void setMonitorRealtimeCpuDAO(MonitorRealtimeCpuDAO monitorRealtimeCpuDAO) {
		super.setDao(monitorRealtimeCpuDAO);
		this.monitorRealtimeCpuDAO = monitorRealtimeCpuDAO;
	}
	
	

}
