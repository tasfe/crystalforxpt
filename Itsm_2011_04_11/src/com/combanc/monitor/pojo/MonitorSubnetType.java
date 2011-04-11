package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorSubnetType entity. @author MyEclipse Persistence Tools
 */

public class MonitorSubnetType implements java.io.Serializable {

	// Fields

	private Integer code;
	private String name;
	private Integer isuse;
	private Set monitorSubnets = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorSubnetType() {
	}

	/** full constructor */
	public MonitorSubnetType(String name, Integer isuse, Set monitorSubnets) {
		this.name = name;
		this.isuse = isuse;
		this.monitorSubnets = monitorSubnets;
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

	public Set getMonitorSubnets() {
		return this.monitorSubnets;
	}

	public void setMonitorSubnets(Set monitorSubnets) {
		this.monitorSubnets = monitorSubnets;
	}

	

}