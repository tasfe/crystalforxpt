package com.combanc.monitor.util.listener;

import java.util.Date;

import com.combanc.monitor.dao.MonitorTopologyEdgeDAO;
import com.combanc.monitor.dao.MonitorTopologyNodeDAO;
import com.combanc.monitor.service.MonitorRealtimeCpuService;
import com.combanc.monitor.service.MonitorRealtimeDelayService;
import com.combanc.monitor.service.MonitorRealtimeFaultService;
import com.combanc.monitor.service.MonitorRealtimePortflowService;
import com.combanc.monitor.service.MonitorRealtimeUseService;
import com.combanc.monitor.util.ApplicationContextUtil;
  

/**
 * 要执行的任务： 清空实时表
 * @author Administrator
 *
 */
public class TruncateTableTask extends Thread{

	public void run(){
		 
		System.out.println("清空实时CPU表,延时表,故障表,接口流量表,可用表,拓扑数据表");
		//清空实时CPU表
		MonitorRealtimeCpuService monitorRealtimeCpuService = (MonitorRealtimeCpuService)ApplicationContextUtil.getContext().getBean("MonitorRealtimeCpuService");
		monitorRealtimeCpuService.truncateTable();
		//清空实时延时表
		MonitorRealtimeDelayService monitorRealtimeDelayService = (MonitorRealtimeDelayService)ApplicationContextUtil.getContext().getBean("MonitorRealtimeDelayService");
		monitorRealtimeDelayService.truncateTable();
		//清空实时故障表
		MonitorRealtimeFaultService monitorRealtimeFaultService = (MonitorRealtimeFaultService)ApplicationContextUtil.getContext().getBean("MonitorRealtimeFaultService");
		monitorRealtimeFaultService.truncateTable();
		
		//清空实时接口流量表
		MonitorRealtimePortflowService monitorRealtimePortflowService = (MonitorRealtimePortflowService)ApplicationContextUtil.getContext().getBean("MonitorRealtimePortflowService");
		monitorRealtimePortflowService.truncateTable();
		
		//清空实时可用表
		MonitorRealtimeUseService monitorRealtimeUseService = (MonitorRealtimeUseService)ApplicationContextUtil.getContext().getBean("MonitorRealtimeUseService");
		monitorRealtimeUseService.truncateTable();
		
		//清空拓扑数据表
		MonitorTopologyEdgeDAO monitorTopologyEdgeDAO = (MonitorTopologyEdgeDAO)ApplicationContextUtil.getContext().getBean("MonitorTopologyEdgeDAO");
		monitorTopologyEdgeDAO.truncateTable();
		MonitorTopologyNodeDAO monitorTopologyNodeDAO = (MonitorTopologyNodeDAO)ApplicationContextUtil.getContext().getBean("MonitorTopologyNodeDAO");
		monitorTopologyNodeDAO.truncateTable();
		
		
		
	}

}
