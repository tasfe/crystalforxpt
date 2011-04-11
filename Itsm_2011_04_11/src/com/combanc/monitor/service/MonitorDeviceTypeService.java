package com.combanc.monitor.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorDeviceTypeDAO;
import com.combanc.monitor.pojo.MonitorDeviceType;

public class MonitorDeviceTypeService extends BaseService<MonitorDeviceType, Integer>{
	
	private MonitorDeviceTypeDAO monitorDeviceTypeDAO;

	public MonitorDeviceTypeDAO getMonitorDeviceTypeDAO() {
		return monitorDeviceTypeDAO;
	}

	public void setMonitorDeviceTypeDAO(MonitorDeviceTypeDAO monitorDeviceTypeDAO) {
		super.setDao(monitorDeviceTypeDAO);
		this.monitorDeviceTypeDAO = monitorDeviceTypeDAO;
	}
	
	
	public MonitorDeviceType getById(int id){
		return monitorDeviceTypeDAO.findById(id);
	}
	
	

}
