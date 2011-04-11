package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.util.TimeUtil;
import com.combanc.monitor.dao.MonitorAccessLogDAO;
import com.combanc.monitor.dao.MonitorArpAlertWhiteMacDAO;
import com.combanc.monitor.pojo.MonitorArpAlertWhiteMac;
import com.combanc.monitor.pojo.MonitorDevice;

public class MonitorArpAlertWhiteMacService extends BaseService<MonitorArpAlertWhiteMac, Integer>{

	private MonitorArpAlertWhiteMacDAO monitorArpAlertWhiteMacDAO;

	
	public MonitorArpAlertWhiteMacDAO getMonitorArpAlertWhiteMacDAO() {
		return monitorArpAlertWhiteMacDAO;
	}

	public void setMonitorArpAlertWhiteMacDAO(
			MonitorArpAlertWhiteMacDAO monitorArpAlertWhiteMacDAO) {
		super.setDao(monitorArpAlertWhiteMacDAO);
		this.monitorArpAlertWhiteMacDAO = monitorArpAlertWhiteMacDAO;
	}


	public MonitorArpAlertWhiteMac findByMac(String mac){
		 
		List<MonitorArpAlertWhiteMac> result = monitorArpAlertWhiteMacDAO.findByMac(mac);
		if(result.size()>0){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	
	
	 
}
