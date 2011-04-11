package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.dao.MonitorRegularDataDAO;
import com.combanc.monitor.pojo.MonitorCpuData;
import com.combanc.monitor.pojo.MonitorRegularData;

public class MonitorRegularDataService extends BaseService<MonitorRegularData, Integer>{

	private MonitorRegularDataDAO monitorRegularDataDAO;
	
	public MonitorRegularDataDAO getMonitorRegularDataDAO() {
		return monitorRegularDataDAO;
	}

	public void setMonitorRegularDataDAO(MonitorRegularDataDAO monitorRegularDataDAO) {
		this.monitorRegularDataDAO = monitorRegularDataDAO;
	}

	public void batchInsert(List regulardataList){
		monitorRegularDataDAO.batchInsert(regulardataList);
	}
	
	public SqlRowSet queryForRowSet(String sql) {
		return monitorRegularDataDAO.getJdbcTemplate().queryForRowSet(sql);
	}
	
	public List queryForList(String sql) {
		return monitorRegularDataDAO.getJdbcTemplate().queryForList(sql);
	}
	
	
	/**
	 * 计算两条记录间的流量差
	 * @param decList
	 */
	public void plusArrayListForMerge(List<MonitorRegularData> decList) {
		if( decList == null ){
			return;
		}
		MonitorRegularData temp = new MonitorRegularData();
		int i = 1;
		long[] flux;
		for(MonitorRegularData rd:decList) {
			if(i==1){
				temp = rd;
			} else {
				long ldate1 = temp.getGatherTime().getTime();
			    long ldate2 = rd.getGatherTime().getTime();
			    long diff = ldate2-ldate1;
				if( diff>=4*1000*60 && diff<=7*1000*60 ) {
					flux = Tools.compuData(temp.getRxByte(), temp.getTxByte(), rd.getRxByte(), rd.getTxByte());
					rd.setReceiveTraffic((flux[2]/(diff/1000)*(5*60)));
					rd.setDeliveryTraffic((flux[1]/(diff/1000)*(5*60)));
					rd.setBiTraffic((flux[0]/(diff/1000)*(5*60)));
				}
				temp = rd;
			}
			i++;
		}
	}
	
	/**
	 * 计算两条记录间的流量差
	 * @param decList
	 */
	public  void plusArrayListForDisplay(List<MonitorRegularData> decList) {
		if( decList == null ){
			return;
		}
		MonitorRegularData temp = new MonitorRegularData();
		int i = 1;
		long[] flux;
		for(MonitorRegularData rd:decList) {
			if(i==1){
				temp = rd;
				rd.setReceiveTraffic(0L);
				rd.setDeliveryTraffic(0L);
				rd.setBiTraffic(0L);
			} else {
				long ldate1 = temp.getGatherTime().getTime();
			    long ldate2 = rd.getGatherTime().getTime();
			    long diff = ldate2-ldate1;
				if( diff>=4*1000*60 && diff<=7*1000*60 ) {
					flux = Tools.compuData(temp.getRxByte(),temp.getTxByte(), rd.getRxByte(), rd.getTxByte());
					rd.setReceiveTraffic((flux[2]/(diff/1000)*(5*60)));
					rd.setDeliveryTraffic((flux[1]/(diff/1000)*(5*60)));
					rd.setBiTraffic((flux[0]/(diff/1000)*(5*60)));
				}else{
					rd.setReceiveTraffic(0L);
					rd.setDeliveryTraffic(0L);
					rd.setBiTraffic(0L);
				}
				
				temp = rd;
			}
			i++;
		}
	}	
	
	/**查找最近一次记录**/
	public List<MonitorRegularData> findLatest(){
		List<MonitorRegularData> regularDataList=new ArrayList<MonitorRegularData>();
		String sql="select a.ID,a.IP,a.INTERFACE,a.TX_BYTE,a.RX_BYTE, a.GATHER_TIME from monitor_regular_data a," +
				" (select IP,INTERFACE,max(GATHER_TIME) as GATHER_TIME from monitor_regular_data  group by IP ,INTERFACE) b" +
				" where a.IP=b.IP and a.INTERFACE=b.INTERFACE and a.GATHER_TIME=b.GATHER_TIME";
		List list=monitorRegularDataDAO.getJdbcTemplate().queryForList(sql);
		if(null!=list&&list.size()>0){
			Iterator it=list.iterator();
			while(it.hasNext()){
				Map model=(Map) it.next();
				MonitorRegularData po=new MonitorRegularData();
				po.setId(Integer.valueOf(model.get("ID").toString()));
				po.setIp(model.get("IP").toString());
				po.setInterface_(model.get("INTERFACE").toString());
				po.setTxByte(Long.parseLong(model.get("TX_BYTE").toString()));
				po.setRxByte(Long.parseLong(model.get("RX_BYTE").toString()));
				po.setGatherTime(Timestamp.valueOf(model.get("GATHER_TIME").toString()));
				regularDataList.add(po);
			}
		}  
		return regularDataList;
		
	}
	
	/**查找设备接口历史记录**/
	public List<MonitorRegularData> findInterfaceHistory(String ip, String ifIndex, Timestamp start, Timestamp end){
		List<MonitorRegularData> dataList = monitorRegularDataDAO.findByPropertys(ip, ifIndex, start, end);
		//计算差值
		plusArrayListForDisplay(dataList) ;
		return dataList;
	}
}
