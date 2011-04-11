package com.combanc.monitor.pojo;

/**
 * MonitorPingRegionGroup entity. @author MyEclipse Persistence Tools
 */

public class MonitorPingRegionGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorSubnet monitorSubnet;
	private String name;
	private String note;

	// Constructors

	/** default constructor */
	public MonitorPingRegionGroup() {
	}

	/** minimal constructor */
	public MonitorPingRegionGroup(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public MonitorPingRegionGroup(Integer id, MonitorSubnet monitorSubnet,
			String name, String note) {
		this.id = id;
		this.monitorSubnet = monitorSubnet;
		this.name = name;
		this.note = note;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}