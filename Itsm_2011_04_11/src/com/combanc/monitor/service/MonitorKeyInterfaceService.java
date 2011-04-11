package com.combanc.monitor.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorKeyInterfaceDAO;
import com.combanc.monitor.pojo.MonitorKeyInterface;

public class MonitorKeyInterfaceService extends BaseService<MonitorKeyInterface, Integer> {

	private MonitorKeyInterfaceDAO monitorKeyInterfaceDAO;

	public MonitorKeyInterfaceDAO getMonitorKeyInterfaceDAO() {
		return monitorKeyInterfaceDAO;
	}

	public void setMonitorKeyInterfaceDAO(
			MonitorKeyInterfaceDAO monitorKeyInterfaceDAO) {
		super.setDao(monitorKeyInterfaceDAO);
		this.monitorKeyInterfaceDAO = monitorKeyInterfaceDAO;
	}
	
	public boolean add(MonitorKeyInterface entity){
		try{
			this.monitorKeyInterfaceDAO.save(entity);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public void isMonitor(Boolean isMonitor ){
		 String hql = "update MonitorKeyInterface p set p.isMonitor="+isMonitor;
		 monitorKeyInterfaceDAO.updateBySql(hql);
	}
	
}
