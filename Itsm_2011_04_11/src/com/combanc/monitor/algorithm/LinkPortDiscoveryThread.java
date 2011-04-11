package com.combanc.monitor.algorithm;

import org.springframework.context.ApplicationContext;

import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.util.ApplicationContextUtil;

public class LinkPortDiscoveryThread extends Thread {
	
	private LinkPortDiscovery linkPortDiscovery;
	
	public LinkPortDiscoveryThread(MonitorSubnet monitorSubnet,String core){
		
		linkPortDiscovery = (LinkPortDiscovery) ApplicationContextUtil.getContext().getBean("LinkPortDiscovery");
		linkPortDiscovery.init(monitorSubnet, core);
	}
	
	 
	public void run() {
		try {
			 linkPortDiscovery.run();
		}catch (Exception e) {
			e.printStackTrace(); 
		}
	}

}
