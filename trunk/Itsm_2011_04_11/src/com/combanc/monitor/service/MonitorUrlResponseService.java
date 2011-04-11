package com.combanc.monitor.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorUrlResponseDAO;
import com.combanc.monitor.pojo.MonitorTcpPort;
import com.combanc.monitor.pojo.MonitorUrlResponse;
import com.combanc.monitor.vo.urlMonitor.factory.MonitorFactory;
import com.combanc.monitor.vo.urlMonitor.model.IMonitor;
import com.combanc.monitor.vo.urlMonitor.model.MonitorResult;

public class MonitorUrlResponseService extends BaseService<MonitorUrlResponse, Integer>{
	
	private MonitorUrlResponseDAO monitorUrlResponseDAO;

	// 执行
	public static MonitorResult testUrl(MonitorUrlResponse url, boolean bTest) {
		if (url != null) {
			String nodeAddr = url.getServerIp();
			if (nodeAddr == null || nodeAddr.trim().isEmpty()) {
				if (bTest) {
					System.out.print("无法测试,因为节点地址或监测参数为空！");
					//JOptionPane.showMessageDialog(null, "无法测试,因为节点地址或监测参数为空！");
				} else {
					System.out.println("无法测试,因为节点地址或监测参数为空！");
				}
				return null;
			}
			
			IMonitor monitor = null;
			try {
				String type = url.getType()==1 ? "HTTP" : "HTTPS";
				monitor = MonitorFactory.getFactory().getMonitor(type);
			} catch (Exception ex) {
				if (bTest) {
					System.out.print("获取监测器时发生错误！");
					//JOptionPane.showMessageDialog(null, "获取监测器时发生错误！");
				} else {
					System.out.println("获取监测器时发生错误！");
				}
				return null;
			}

			MonitorResult result = null;
			try {
				result = monitor.doMonitor(url);
			} catch (Exception ex) {
				if (bTest) {
					System.out.print("获取监测器时发生错误！");
					//JOptionPane.showMessageDialog(null, "获取监测器时发生错误！");
				} else {
					System.out.println("执行监测时发生错误！");
				}
				return null;
			}
			if (result == null) {
				if (bTest) {
					System.out.print("发生错误,监测器返回的监测结果对象为空！");
					//JOptionPane.showMessageDialog(null, "发生错误,监测器返回的监测结果对象为空!");
				} else {
					System.out.println("发生错误,监测器返回的监测结果对象为空!");
				}
				return null;
			} else {
				return result;
			}
		} else {
			return null;
		}
	}
	
	public MonitorUrlResponseDAO getMonitorUrlResponseDAO() {
		return monitorUrlResponseDAO;
	}

	public void setMonitorUrlResponseDAO(MonitorUrlResponseDAO monitorUrlResponseDAO) {
		super.setDao(monitorUrlResponseDAO);
		this.monitorUrlResponseDAO = monitorUrlResponseDAO;
	}
	
	

}
