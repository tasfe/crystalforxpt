package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorRegularDataHourDAO;
import com.combanc.monitor.pojo.MonitorRegularData;
import com.combanc.monitor.pojo.MonitorRegularDataHour;

public class MonitorRegularDataHourService extends BaseService<MonitorRegularDataHour, Integer>{

	private MonitorRegularDataHourDAO monitorRegularDataHourDAO;

	public MonitorRegularDataHourDAO getMonitorRegularDataHourDAO() {
		return monitorRegularDataHourDAO;
	}

	public void setMonitorRegularDataHourDAO(
			MonitorRegularDataHourDAO monitorRegularDataHourDAO) {
		super.setDao(monitorRegularDataHourDAO);
		this.monitorRegularDataHourDAO = monitorRegularDataHourDAO;
	}
	public void batchInsert(List regulardataList){
		monitorRegularDataHourDAO.batchInsert(regulardataList);
	}
	
	public SqlRowSet queryForRowSet(String sql) {
		return monitorRegularDataHourDAO.getJdbcTemplate().queryForRowSet(sql);
	}
	
	/**查找设备接口历史记录**/
	public List<MonitorRegularDataHour> findInterfaceHistory(String ip, String ifIndex, Timestamp start, Timestamp end){
		List<MonitorRegularDataHour> dataList = monitorRegularDataHourDAO.findByPropertys(ip, ifIndex, start, end);
		return dataList;
	}
	
}
