package com.combanc.monitor.pojo;

/**
 * MonitorSubnetDevice entity. @author MyEclipse Persistence Tools
 */

public class MonitorSubnetDevice implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorSubnet monitorSubnet;
	private MonitorDevice monitorDevice;
	private Integer locationX;
	private Integer locationY;
	private String icon;
	private Integer selected;
	private Integer monitored;
	private Integer isLink;

	// Constructors

	/** default constructor */
	public MonitorSubnetDevice() {
	}

	/** full constructor */
	public MonitorSubnetDevice(MonitorSubnet monitorSubnet,
			MonitorDevice monitorDevice) {
		this.monitorSubnet = monitorSubnet;
		this.monitorDevice = monitorDevice;
	}
	public MonitorSubnetDevice(MonitorSubnet monitorSubnet,
			MonitorDevice monitorDevice,Integer locationX,Integer locationY,String icon,Integer selected,Integer monitored) {
		this.monitorSubnet = monitorSubnet;
		this.monitorDevice = monitorDevice;
		this.locationX=locationX;
		this.locationY=locationY;
		this.icon=icon;
		this.selected=selected;
		this.monitored=monitored;
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Integer getLocationX() {
		return locationX;
	}

	public void setLocationX(Integer locationX) {
		this.locationX = locationX;
	}

	public Integer getLocationY() {
		return locationY;
	}

	public void setLocationY(Integer locationY) {
		this.locationY = locationY;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public Integer getMonitored() {
		return monitored;
	}

	public void setMonitored(Integer monitored) {
		this.monitored = monitored;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorSubnet getMonitorSubnet() {
		return this.monitorSubnet;
	}

	public void setMonitorSubnet(MonitorSubnet monitorSubnet) {
		this.monitorSubnet = monitorSubnet;
	}

	public MonitorDevice getMonitorDevice() {
		return this.monitorDevice;
	}

	public void setMonitorDevice(MonitorDevice monitorDevice) {
		this.monitorDevice = monitorDevice;
	}

	public Integer getIsLink() {
		return isLink;
	}

	public void setIsLink(Integer isLink) {
		this.isLink = isLink;
	}
	

}