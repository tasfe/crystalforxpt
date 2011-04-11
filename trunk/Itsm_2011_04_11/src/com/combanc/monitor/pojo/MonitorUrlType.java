package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorUrlType entity. @author MyEclipse Persistence Tools
 */

public class MonitorUrlType implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String description;
	private String configer;
	private String monitor;
	private String extendOid;
	private Integer sort;
	private Integer autoDiscover;
	private String ports;
	private Integer timeout;
	private Integer monitorInterval;
	private Integer erratumInterval;
	private Set monitorUrlServices = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorUrlType() {
	}

	/** full constructor */
	public MonitorUrlType(String name, String description, String configer,
			String monitor, String extendOid, Integer sort,
			Integer autoDiscover, String ports, Integer timeout,
			Integer monitorInterval, Integer erratumInterval,
			Set monitorUrlServices) {
		this.name = name;
		this.description = description;
		this.configer = configer;
		this.monitor = monitor;
		this.extendOid = extendOid;
		this.sort = sort;
		this.autoDiscover = autoDiscover;
		this.ports = ports;
		this.timeout = timeout;
		this.monitorInterval = monitorInterval;
		this.erratumInterval = erratumInterval;
		this.monitorUrlServices = monitorUrlServices;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getConfiger() {
		return this.configer;
	}

	public void setConfiger(String configer) {
		this.configer = configer;
	}

	public String getMonitor() {
		return this.monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getExtendOid() {
		return this.extendOid;
	}

	public void setExtendOid(String extendOid) {
		this.extendOid = extendOid;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getAutoDiscover() {
		return this.autoDiscover;
	}

	public void setAutoDiscover(Integer autoDiscover) {
		this.autoDiscover = autoDiscover;
	}

	public String getPorts() {
		return this.ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public Integer getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getMonitorInterval() {
		return this.monitorInterval;
	}

	public void setMonitorInterval(Integer monitorInterval) {
		this.monitorInterval = monitorInterval;
	}

	public Integer getErratumInterval() {
		return this.erratumInterval;
	}

	public void setErratumInterval(Integer erratumInterval) {
		this.erratumInterval = erratumInterval;
	}

	public Set getMonitorUrlServices() {
		return this.monitorUrlServices;
	}

	public void setMonitorUrlServices(Set monitorUrlServices) {
		this.monitorUrlServices = monitorUrlServices;
	}

}