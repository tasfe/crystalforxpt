package com.combanc.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.dao.MonitorAlertSmalltypeDAO;
import com.combanc.monitor.dao.MonitorComputerDAO;
import com.combanc.monitor.pojo.MonitorAlertSmalltype;
import com.combanc.monitor.pojo.MonitorComputer;

public class MonitorAlertSmalltypeService extends BaseService<MonitorAlertSmalltype, Integer> {
	
	private MonitorAlertSmalltypeDAO monitorAlertSmalltypeDAO;

	public MonitorAlertSmalltypeDAO getMonitorAlertSmalltypeDAO() {
		return monitorAlertSmalltypeDAO;
	}

	public void setMonitorAlertSmalltypeDAO(
			MonitorAlertSmalltypeDAO monitorAlertSmalltypeDAO) {
		super.setDao(monitorAlertSmalltypeDAO);
		this.monitorAlertSmalltypeDAO = monitorAlertSmalltypeDAO;
	}
	
	public List<MonitorAlertSmalltype> findByParentTypeId(String parentTypeId){
		if(null == parentTypeId) return null;
		if("all".equals(parentTypeId)){
			return monitorAlertSmalltypeDAO.findAll();
		}else{
			return monitorAlertSmalltypeDAO.findByProperty("pcode", Integer.parseInt(parentTypeId));
		}
			
	}
}

