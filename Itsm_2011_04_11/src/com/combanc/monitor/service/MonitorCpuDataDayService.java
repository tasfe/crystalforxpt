package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorCpuDataDayDAO;
import com.combanc.monitor.pojo.MonitorCpuDataDay;
import com.combanc.monitor.pojo.MonitorCpuDataHour;

public class MonitorCpuDataDayService  extends BaseService<MonitorCpuDataDay, Integer>{
	
	private MonitorCpuDataDayDAO monitorCpuDataDayDAO;

	public MonitorCpuDataDayDAO getMonitorCpuDataDayDAO() {
		return monitorCpuDataDayDAO;
	}

	public void setMonitorCpuDataDayDAO(MonitorCpuDataDayDAO monitorCpuDataDayDAO) {
		this.monitorCpuDataDayDAO = monitorCpuDataDayDAO;
	}
	
	public SqlRowSet queryForRowSet(String sql) {
		return monitorCpuDataDayDAO.getJdbcTemplate().queryForRowSet(sql);
	}
	
	public void batchInsert(List<MonitorCpuDataDay> cpuDataDayList){
		monitorCpuDataDayDAO.batchInsert(cpuDataDayList);
	}
	
	/**
	 * 查找设备CPU数据
	 * @param ip IP地址
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param index 标识
	 * @return
	 */
	public List<MonitorCpuDataDay> findByPropertys(String ip, Timestamp start, Timestamp end, Integer index) {
		return monitorCpuDataDayDAO.findByPropertys(ip,  start, end, index);
	}

}
