package com.combanc.monitor.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorVendorDAO;
import com.combanc.monitor.pojo.MonitorVendor;

public class MonitorVendorService extends BaseService<MonitorVendor, Integer>{
	
	private MonitorVendorDAO monitorVendorDAO;

	public MonitorVendorDAO getMonitorVendorDAO() {
		return monitorVendorDAO;
	}

	public void setMonitorVendorDAO(MonitorVendorDAO monitorVendorDAO) {
		this.monitorVendorDAO = monitorVendorDAO;
	}
	
	public MonitorVendor findByVendor(String Vendor){
		return monitorVendorDAO.findById(Vendor);
		
	}
	

}
