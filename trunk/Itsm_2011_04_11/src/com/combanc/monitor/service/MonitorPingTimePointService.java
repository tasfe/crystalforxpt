package com.combanc.monitor.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorPingTimePointDAO;
import com.combanc.monitor.pojo.MonitorPingTimePoint;

public class MonitorPingTimePointService extends BaseService<MonitorPingTimePoint, Integer>{
	
	private MonitorPingTimePointDAO monitorPingTimePointDAO;

	public MonitorPingTimePointDAO getMonitorPingTimePointDAO() {
		return monitorPingTimePointDAO;
	}

	public void setMonitorPingTimePointDAO(
			MonitorPingTimePointDAO monitorPingTimePointDAO) {
		super.setDao(monitorPingTimePointDAO);
		this.monitorPingTimePointDAO = monitorPingTimePointDAO;
	}
	
	

}
