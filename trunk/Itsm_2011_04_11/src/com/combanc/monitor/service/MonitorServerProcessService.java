package com.combanc.monitor.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorServerProcessDAO;
import com.combanc.monitor.pojo.MonitorServerProcess;

public class MonitorServerProcessService extends BaseService<MonitorServerProcess, Integer>{
	
	private MonitorServerProcessDAO monitorServerProcessDAO;

	public List<MonitorServerProcess> findByProcStart(boolean procStart) {
		return monitorServerProcessDAO.findByProcStart(procStart);
	}
	
	public MonitorServerProcessDAO getMonitorServerProcessDAO() {
		return monitorServerProcessDAO;
	}

	public void setMonitorServerProcessDAO(
			MonitorServerProcessDAO monitorServerProcessDAO) {
		this.monitorServerProcessDAO = monitorServerProcessDAO;
	}
	
	

}
