package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorDeviceType entity. @author MyEclipse Persistence Tools
 */

public class MonitorDeviceType implements java.io.Serializable {

	// Fields

	private Integer code;
	private String name;
	private String icon;
	private Integer isuse;
	private Set monitorDevices = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorDeviceType() {
	}

	/** minimum constructor */
	public MonitorDeviceType(Integer code) {
		this.code = code;
	}

	/** full constructor */
	public MonitorDeviceType(String name, Integer isuse, Set monitorDevices) {
		this.name = name;
		this.isuse = isuse;
		this.monitorDevices = monitorDevices;
	}

	// Property accessors

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsuse() {
		return this.isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public Set getMonitorDevices() {
		return this.monitorDevices;
	}

	public void setMonitorDevices(Set monitorDevices) {
		this.monitorDevices = monitorDevices;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}