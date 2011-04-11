package com.combanc.monitor.pojo;

import java.util.List;

/**
 *  用于保存拓扑属性包括系统参数，互联端口信息列表，设备列表
 * @author dongyp
 *
 */
public class MonitorTopologyParam {
	
	private List<MonitorSystemParam> systemParamList;//系统参数
	
	private List<MonitorSubnetDevice> subnetDeviceList;//设备列表

	private List<MonitorLinkport> linkportList;//互联端口信息列表
	
	private MonitorSubnet subnet;//分区属性

	public List<MonitorSystemParam> getSystemParamList() {
		return systemParamList;
	}

	public void setSystemParamList(List<MonitorSystemParam> systemParamList) {
		this.systemParamList = systemParamList;
	}

	public List<MonitorSubnetDevice> getSubnetDeviceList() {
		return subnetDeviceList;
	}

	public void setSubnetDeviceList(List<MonitorSubnetDevice> subnetDeviceList) {
		this.subnetDeviceList = subnetDeviceList;
	}

	public List<MonitorLinkport> getLinkportList() {
		return linkportList;
	}

	public void setLinkportList(List<MonitorLinkport> linkportList) {
		this.linkportList = linkportList;
	}

	public MonitorSubnet getSubnet() {
		return subnet;
	}

	public void setSubnet(MonitorSubnet subnet) {
		this.subnet = subnet;
	}
	
	
	
	
}
