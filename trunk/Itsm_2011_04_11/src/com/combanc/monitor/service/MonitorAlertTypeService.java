package com.combanc.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorAlertTypeDAO;
import com.combanc.monitor.pojo.MonitorAlertType;


public class MonitorAlertTypeService extends BaseService<MonitorAlertType, Integer> {
	
	private MonitorAlertTypeDAO monitorAlertTypeDAO;

	public MonitorAlertTypeDAO getMonitorAlertTypeDAO() {
		return monitorAlertTypeDAO;
	}

	public void setMonitorAlertTypeDAO(MonitorAlertTypeDAO monitorAlertTypeDAO) {
		super.setDao(monitorAlertTypeDAO);
		this.monitorAlertTypeDAO = monitorAlertTypeDAO;
	}
	
	public List<MonitorAlertType> getAll(){
		return monitorAlertTypeDAO.getAll();
	}
	
}

