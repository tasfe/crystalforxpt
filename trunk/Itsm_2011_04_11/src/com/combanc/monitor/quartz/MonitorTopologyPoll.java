
package com.combanc.monitor.quartz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.adventnet.snmp.beans.SnmpTarget;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.CronTriggerBean;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.dao.MonitorDeviceDAO;
import com.combanc.monitor.dao.MonitorLinkportDAO;
import com.combanc.monitor.dao.MonitorSubnetDeviceDAO;
import com.combanc.monitor.dao.MonitorTopologyEdgeDAO;
import com.combanc.monitor.dao.MonitorTopologyNodeDAO;
import com.combanc.monitor.dao.MonitorVendorDAO;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorTopologyEdge;
import com.combanc.monitor.pojo.MonitorTopologyNode;
import com.combanc.monitor.pojo.MonitorVendor;
import com.combanc.monitor.service.MonitorSubnetService;
import com.combanc.monitor.util.IPUtil;

/**
 * <p>
 * Title:扫描设备信息及互联端口流量信息等保存到表中
 * </p>
 * <p>
 * Description: 是拓扑图刷新数据的来源
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: Combanc
 * </p>
 * @author 
 * @version 
 */
public class MonitorTopologyPoll extends BaseScan{
	
	public static HashMap subnetMap = new HashMap();
	


	private MonitorLinkportDAO monitorLinkportDAO;
	
	private MonitorSubnetService monitorSubnetService;
	

	public MonitorLinkportDAO getMonitorLinkportDAO() {
		return monitorLinkportDAO;
	}
	public void setMonitorLinkportDAO(MonitorLinkportDAO monitorLinkportDAO) {
		this.monitorLinkportDAO = monitorLinkportDAO;
	}
	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}
	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
	}
	public MonitorTopologyPoll(){
		 paramCode = SystemParamConstants.SYSTEM_POLL_GAP;
		 defaultCronExpression = "0 0/1 * * * ?";
		 cronTriggerName ="cronTriggerTopologyPollTask";
 
	}
	/**拓扑轮询**/
	public void startScan(){
		if(MonitorTopologyPoll.subnetMap.isEmpty()) return;
		
		System.out.println("拓扑轮询开始============================");
		Iterator iterator = MonitorTopologyPoll.subnetMap.keySet().iterator();  
		List<Integer> subnetList = new ArrayList<Integer>();
        while (iterator.hasNext()) {
	         Object key = iterator.next();
	         Timestamp queryTime = (Timestamp) MonitorTopologyPoll.subnetMap.get(key);//flex拓扑请求时间
	         long nowTime = System.currentTimeMillis();
	         long timeGap = nowTime - queryTime.getTime();
	         if(timeGap <= SystemParamConstants.SYSTEM_TOPO_REQUEST_TIME_OUT){//一分钟内的视为有效请求，加入分区列表
	        	 subnetList.add((Integer) key);
	         }else{
	        	 iterator.remove();
	         }
        }    
        
        if(subnetList.size()>0){
        	List<MonitorDevice> deviceList = monitorSubnetService.getDeviceBySubnetIdList(subnetList);
        	if(deviceList.size()>0){
        		List<MonitorDevice> newDeviceList = new ArrayList<MonitorDevice>();
        		for(int i=0;i<deviceList.size(); i++){
        			newDeviceList.add(deviceList.get(i));
        			if(newDeviceList.size()>SystemParamConstants.SYSTEM_TOPO_DEVICE_SIZE){
        				GetDeviceInfo thread = new GetDeviceInfo(newDeviceList);
        				thread.start();
        				newDeviceList = new ArrayList<MonitorDevice>();
        			}
        		}
        		if(newDeviceList.size()>0){
        			GetDeviceInfo thread = new GetDeviceInfo(newDeviceList);
    				thread.start();
        		}
        			
        	}
        	List<MonitorLinkport> linkportList = monitorLinkportDAO.findAllUniqueWithDownLink(subnetList);
            if(linkportList.size()>0){
            	List<MonitorLinkport> newLinkPortList = new ArrayList<MonitorLinkport>();
            	for(int i=0;i<linkportList.size(); i++){
            		newLinkPortList.add(linkportList.get(i));
            		if(newLinkPortList.size()>SystemParamConstants.SYSTEM_TOPO_LINKPORT_SIZE){
            			GetLinkPortInfo thread = new GetLinkPortInfo(newLinkPortList);
        				thread.start();
        				newLinkPortList = new ArrayList<MonitorLinkport>();
        			}
            	}
            	if(newLinkPortList.size()>0){
            		GetLinkPortInfo thread = new GetLinkPortInfo(newLinkPortList);
    				thread.start();
        		}
            }
        }
        
		System.out.println("拓扑轮询结束============================");
	}
	 

	
	
	
	
	
	
	
	
	
	
	
	
	 
}
