package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorAlertType entity. @author MyEclipse Persistence Tools
 */

public class MonitorAlertType implements java.io.Serializable {

	// Fields

	private Integer code;
	private String name;
	private Integer isuse;
	private Set monitorAlerts = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorAlertType() {
	}

	/** full constructor */
	public MonitorAlertType(String name, Integer isuse, Set monitorAlerts) {
		this.name = name;
		this.isuse = isuse;
		this.monitorAlerts = monitorAlerts;
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

	public Set getMonitorAlerts() {
		return this.monitorAlerts;
	}

	public void setMonitorAlerts(Set monitorAlerts) {
		this.monitorAlerts = monitorAlerts;
	}

}