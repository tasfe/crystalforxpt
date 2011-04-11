package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorVendorMac entity. @author MyEclipse Persistence Tools
 */

public class MonitorVendorMac implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String mac;
	private Integer isuse;
	private Set monitorDevices = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorVendorMac() {
	}

	/** full constructor */
	public MonitorVendorMac(String name, String mac, Integer isuse,
			Set monitorDevices) {
		this.name = name;
		this.mac = mac;
		this.isuse = isuse;
		this.monitorDevices = monitorDevices;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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

}