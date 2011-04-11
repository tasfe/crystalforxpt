package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorRegularDataDayDAO;
import com.combanc.monitor.pojo.MonitorRegularDataDay;
import com.combanc.monitor.pojo.MonitorRegularDataHour;

public class MonitorRegularDataDayService extends BaseService<MonitorRegularDataDay, Integer>{

	private MonitorRegularDataDayDAO monitorRegularDataDayDAO;

	public MonitorRegularDataDayDAO getMonitorRegularDataDayDAO() {
		return monitorRegularDataDayDAO;
	}

	public void setMonitorRegularDataDayDAO(
			MonitorRegularDataDayDAO monitorRegularDataDayDAO) {
		super.setDao(monitorRegularDataDayDAO);
		this.monitorRegularDataDayDAO = monitorRegularDataDayDAO;
	}
	public void batchInsert(List regulardataList){
		monitorRegularDataDayDAO.batchInsert(regulardataList);
	}
	
	public SqlRowSet queryForRowSet(String sql) {
		return monitorRegularDataDayDAO.getJdbcTemplate().queryForRowSet(sql);
	}
	
	public void delBeforeDate(Timestamp time) {
		monitorRegularDataDayDAO.delBeforeDate(time);
	}
	
	/**查找设备接口历史记录**/
	public List<MonitorRegularDataDay> findInterfaceHistory(String ip, String ifIndex, Timestamp start, Timestamp end){
		List<MonitorRegularDataDay> dataList = monitorRegularDataDayDAO.findByPropertys(ip, ifIndex, start, end);
		return dataList;
	}
}
