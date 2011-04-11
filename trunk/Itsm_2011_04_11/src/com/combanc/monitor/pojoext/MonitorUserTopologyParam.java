package com.combanc.monitor.pojoext;

import java.util.List;

import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorSystemParam;

/**
 *  用于保存用户拓扑属性包括系统参数，设备端口信息列表，入网计算机列表
 * @author lsj
 *
 */
public class MonitorUserTopologyParam {
	
	private List<MonitorSystemParam> systemParamList;//系统参数
	
	private List<MonitorInterfaceCacheExt> interfaceCacheExtList;//设备端口列表

	private List<MonitorComputer> computerList;//入网计算机列表
	
	private MonitorDevice monitorDevice;		// 设备PO

	public List<MonitorSystemParam> getSystemParamList() {
		return systemParamList;
	}

	public void setSystemParamList(List<MonitorSystemParam> systemParamList) {
		this.systemParamList = systemParamList;
	}

	public void setInterfaceCacheExtList(List<MonitorInterfaceCacheExt> interfaceCacheExtList) {
		this.interfaceCacheExtList = interfaceCacheExtList;
	}

	public List<MonitorInterfaceCacheExt> getInterfaceCacheExtList() {
		return interfaceCacheExtList;
	}

	public void setComputerList(List<MonitorComputer> computerList) {
		this.computerList = computerList;
	}

	public List<MonitorComputer> getComputerList() {
		return computerList;
	}

	public void setMonitorDevice(MonitorDevice monitorDevice) {
		this.monitorDevice = monitorDevice;
	}

	public MonitorDevice getMonitorDevice() {
		return monitorDevice;
	}
}
