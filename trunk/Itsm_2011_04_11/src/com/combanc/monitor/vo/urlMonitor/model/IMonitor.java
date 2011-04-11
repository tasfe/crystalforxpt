package com.combanc.monitor.vo.urlMonitor.model;

import com.combanc.monitor.pojo.MonitorUrlResponse;

public interface IMonitor {
	
	public abstract void setDebug(boolean paramBoolean);

	public abstract MonitorResult doMonitor(MonitorUrlResponse httpUrlResponse)
			throws Exception;
}
