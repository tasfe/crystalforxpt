package com.combanc.monitor.vo.urlMonitor.factory;

import java.util.HashMap;
import java.util.Map;

import com.combanc.monitor.vo.urlMonitor.http.HTTPMonitor;
import com.combanc.monitor.vo.urlMonitor.https.HTTPSMonitor;
import com.combanc.monitor.vo.urlMonitor.model.IMonitor;

public class MonitorFactory {

	private Map monitors = new HashMap();

	private static MonitorFactory factory = new MonitorFactory();

	public static MonitorFactory getFactory() {
		return factory;
	}
	
	public IMonitor getMonitor(String type) throws Exception {
		IMonitor monitor = (IMonitor) this.monitors.get(type);
		if (monitor != null) {
			return monitor;
		}
		
		if (!"HTTPS".equalsIgnoreCase(type) && !"HTTP".equalsIgnoreCase(type)) {
			throw new IllegalArgumentException("非法访问,不存在的监测器ID=" + type + ".");
		} else {
			try {
				if ("HTTPS".equalsIgnoreCase(type)) {
					monitor = new HTTPSMonitor();
				} else {
					monitor = new HTTPMonitor();
				}
			} catch (Exception ex) {
				throw ex;
			}
			this.monitors.put(type, monitor);
		}
		return monitor;
	}

}
