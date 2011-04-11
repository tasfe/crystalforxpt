package com.combanc.monitor.service;

import java.util.Iterator;
import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorRealtimeUseDAO;
import com.combanc.monitor.pojo.MonitorRealtimeUse;

public class MonitorRealtimeUseService extends BaseService<MonitorRealtimeUse, Integer>{
	
	private MonitorRealtimeUseDAO monitorRealtimeUseDAO;
	
	/**
	 * 检查对象是否在可用表中，并修改累计数和有效数
	 * @param useList 可用表对象
	 * @param use 被检查的可用对象
	 * @param inc
	 * @return
	 */
	public Boolean locate(List<MonitorRealtimeUse> useList,MonitorRealtimeUse use,double inc){
		 Boolean isLocate=false;
			Iterator itr = useList.iterator();
			while (itr.hasNext()) {
				MonitorRealtimeUse newUse = (MonitorRealtimeUse) itr.next();
				if(newUse.getIp().equals(use.getIp())&&newUse.getType().equals(use.getType()))
				{
					use=newUse;
					isLocate=true;
					newUse.setTotalNum(newUse.getTotalNum()+1);
					newUse.setEffectiveNum(newUse.getEffectiveNum()+(float)inc);
					newUse.setName(newUse.getName());
					newUse.setExist(true);
					break;
				}
			}

			return isLocate;
	}
	/**批量插入**/
	public int[] batchInsert(List< MonitorRealtimeUse> realtimeUseList){
		return monitorRealtimeUseDAO.batchInsert(realtimeUseList);
		
	}
	
	/**topN**/
	public List<MonitorRealtimeUse> findTopN(Integer topN){
		return monitorRealtimeUseDAO.findTopN(topN);
	}
	
	/**topN**/
	public List<MonitorRealtimeUse> findTopN(Integer subnetId,Integer topN){
		return monitorRealtimeUseDAO.findTopN(subnetId,topN);
	}
	public MonitorRealtimeUseDAO getMonitorRealtimeUseDAO() {
		return monitorRealtimeUseDAO;
	}

	public void setMonitorRealtimeUseDAO(MonitorRealtimeUseDAO monitorRealtimeUseDAO) {
		super.setDao(monitorRealtimeUseDAO);
		this.monitorRealtimeUseDAO = monitorRealtimeUseDAO;
	}
	
	

}
