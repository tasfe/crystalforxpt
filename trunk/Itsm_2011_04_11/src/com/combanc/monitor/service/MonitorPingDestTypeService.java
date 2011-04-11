package com.combanc.monitor.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorPingDestTypeDAO;
import com.combanc.monitor.pojo.MonitorPingDestType;

public class MonitorPingDestTypeService extends BaseService<MonitorPingDestType, Integer>{

	private MonitorPingDestTypeDAO monitorPingDestTypeDAO;

	public MonitorPingDestTypeDAO getMonitorPingDestTypeDAO() {
		return monitorPingDestTypeDAO;
	}

	public void setMonitorPingDestTypeDAO(
			MonitorPingDestTypeDAO monitorPingDestTypeDAO) {
		super.setDao(monitorPingDestTypeDAO);
		this.monitorPingDestTypeDAO = monitorPingDestTypeDAO;
	}
	
}
