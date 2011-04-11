package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorCpuDataDAO;
import com.combanc.monitor.pojo.MonitorCpuData;

public class MonitorCpuDataService  extends BaseService<MonitorCpuData, Integer>{
	
	private MonitorCpuDataDAO monitorCpuDataDAO;
	
	
	public MonitorCpuDataDAO getMonitorCpuDataDAO() {
		return monitorCpuDataDAO;
	}

	public void setMonitorCpuDataDAO(MonitorCpuDataDAO monitorCpuDataDAO) {
		this.monitorCpuDataDAO = monitorCpuDataDAO;
	}

	public SqlRowSet queryForRowSet(String sql) {
		return monitorCpuDataDAO.getJdbcTemplate().queryForRowSet(sql);
	}
	
	public List queryForList(String sql) {
		return monitorCpuDataDAO.getJdbcTemplate().queryForList(sql);
	}
	
	
	/**查找所有设备最近记录**/
	public List<MonitorCpuData> findLatest(){
		List<MonitorCpuData> cpuDataList=new ArrayList<MonitorCpuData>();
		String sql="select a.ID,a.IP,a.CPU ,a.GATHER_TIME from monitor_cpu_data a," +
				" (select IP,max(GATHER_TIME) as GATHER_TIME from monitor_cpu_data group by IP) b" +
				" where a.IP=b.IP and a.GATHER_TIME=b.GATHER_TIME  and a.DATA_INDEX=1 order by CPU desc;";
		List list=monitorCpuDataDAO.getJdbcTemplate().queryForList(sql);
		if(null!=list&&list.size()>0){
			Iterator it=list.iterator();
			while(it.hasNext()){
				Map model=(Map) it.next();
				MonitorCpuData po=new MonitorCpuData();
				po.setIp(model.get("IP").toString());
				po.setCpu(Float.valueOf(model.get("CPU").toString()));
				po.setGatherTime(Timestamp.valueOf(model.get("GATHER_TIME").toString()));
				po.setDataIndex(1);
				cpuDataList.add(po);
			}
		}  
		return cpuDataList;
	}
	/**
	 * 查找设备CPU数据
	 * @param ip IP地址
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param index 标识
	 * @return
	 */
	public List<MonitorCpuData> findByPropertys(String ip, Timestamp start, Timestamp end, Integer index) {
		return monitorCpuDataDAO.findByPropertys(ip,  start, end, index);
	}
}
