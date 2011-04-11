package com.combanc.monitor.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorSegmentDAO;
import com.combanc.monitor.pojo.MonitorSegment;

public class MonitorSegmentService extends BaseService<MonitorSegment, Integer>{
	
	private MonitorSegmentDAO monitorSegmentDAO;
	
	 
	public MonitorSegmentDAO getMonitorSegmentDAO() {
		return monitorSegmentDAO;
	}
	public void setMonitorSegmentDAO(MonitorSegmentDAO monitorSegmentDAO) {
		super.setDao(monitorSegmentDAO);
		this.monitorSegmentDAO = monitorSegmentDAO;
	}

}
