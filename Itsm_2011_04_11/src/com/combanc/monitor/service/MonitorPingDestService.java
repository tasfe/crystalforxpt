package com.combanc.monitor.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorPingDestDAO;
import com.combanc.monitor.pojo.MonitorPingDest;

public class MonitorPingDestService extends BaseService<MonitorPingDest, Integer>{
	
	private MonitorPingDestDAO monitorPingDestDAO;

	public MonitorPingDestDAO getMonitorPingDestDAO() {
		return monitorPingDestDAO;
	}

	public void setMonitorPingDestDAO(MonitorPingDestDAO monitorPingDestDAO) {
		super.setDao(monitorPingDestDAO);
		this.monitorPingDestDAO = monitorPingDestDAO;
	}
	

}
