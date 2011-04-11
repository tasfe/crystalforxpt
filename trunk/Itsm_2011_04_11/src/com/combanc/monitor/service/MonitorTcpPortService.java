package com.combanc.monitor.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorTcpPortDAO;
import com.combanc.monitor.pojo.MonitorTcpPort;

public class MonitorTcpPortService  extends BaseService<MonitorTcpPort, Integer>{
	
	private MonitorTcpPortDAO monitorTcpPortDAO;
	
	public List<MonitorTcpPort> findAll(){
		return monitorTcpPortDAO.findAll();
	}
	
	public MonitorTcpPort findByPortNumber(String portNumber){
		if(null==portNumber||"".equals(portNumber))
			return null;
		else
			return monitorTcpPortDAO.findById(portNumber);
	}
	
	public MonitorTcpPort findByPortName(String portName){
		if(null==portName||"".equals(portName))
			return null;
		else{
			List<MonitorTcpPort> list =  monitorTcpPortDAO.findByPortName(portName);
			if(list.size()>0)
				return list.get(0);
			else 
				return null;
		}
			
	}
	
	public MonitorTcpPortDAO getMonitorTcpPortDAO() {
		return monitorTcpPortDAO;
	}

	public void setMonitorTcpPortDAO(MonitorTcpPortDAO monitorTcpPortDAO) {
		this.monitorTcpPortDAO = monitorTcpPortDAO;
	}
	
	
	
	

}
