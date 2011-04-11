package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorCpuDataHourDAO;
import com.combanc.monitor.pojo.MonitorCpuData;
import com.combanc.monitor.pojo.MonitorCpuDataHour;

public class MonitorCpuDataHourService extends BaseService<MonitorCpuDataHour, Integer>{
	
	private MonitorCpuDataHourDAO monitorCpuDataHourDAO;

	public MonitorCpuDataHourDAO getMonitorCpuDataHourDAO() {
		return monitorCpuDataHourDAO;
	}

	public void setMonitorCpuDataHourDAO(MonitorCpuDataHourDAO monitorCpuDataHourDAO) {
		this.monitorCpuDataHourDAO = monitorCpuDataHourDAO;
	}

	public void batchInsert(List<MonitorCpuDataHour> cpuDataHourList){
		monitorCpuDataHourDAO.batchInsert(cpuDataHourList);
	}

	/**
	 * 查找设备CPU数据
	 * @param ip IP地址
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param index 标识
	 * @return
	 */
	public List<MonitorCpuDataHour> findByPropertys(String ip, Timestamp start, Timestamp end, Integer index) {
		return monitorCpuDataHourDAO.findByPropertys(ip,  start, end, index);
	}
}
