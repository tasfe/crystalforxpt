package com.combanc.monitor.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorPingResultDAO;
import com.combanc.monitor.pojo.MonitorPingResult;

public class MonitorPingResultService  extends BaseService<MonitorPingResult, Integer> {
	
	private MonitorPingResultDAO monitorPingResultDAO;

	public MonitorPingResultDAO getMonitorPingResultDAO() {
		return monitorPingResultDAO;
	}

	public void setMonitorPingResultDAO(MonitorPingResultDAO monitorPingResultDAO) {
		super.setDao(monitorPingResultDAO);
		this.monitorPingResultDAO = monitorPingResultDAO;
	}
	
	public void batchInsert(List<MonitorPingResult> pingResultList){
		monitorPingResultDAO.batchInsert(pingResultList);
	}
	
}
