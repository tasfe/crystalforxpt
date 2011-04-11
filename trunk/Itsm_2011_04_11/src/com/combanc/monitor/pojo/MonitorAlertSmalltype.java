package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorAlertSmalltype entity. @author MyEclipse Persistence Tools
 */

public class MonitorAlertSmalltype implements java.io.Serializable {

	// Fields

	private Integer code;
	private String name;
	private Integer pcode;
	private Integer isuse;
	private Integer level;
	private String  description;
	private Set monitorAlerts = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorAlertSmalltype() {
	}

	/** full constructor */
	public MonitorAlertSmalltype(String name, Integer pcode, Integer isuse,Integer level,String description,
			Set monitorAlerts) {
		this.name = name;
		this.pcode = pcode;
		this.isuse = isuse;
		this.level = level;
		this.description = description;
		this.monitorAlerts = monitorAlerts;
	}
	public MonitorAlertSmalltype(Integer code) {
		this.code = code;
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

	public Integer getPcode() {
		return this.pcode;
	}

	public void setPcode(Integer pcode) {
		this.pcode = pcode;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}