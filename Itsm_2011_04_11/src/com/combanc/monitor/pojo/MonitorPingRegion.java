package com.combanc.monitor.pojo;

/**
 * MonitorPingRegion entity. @author MyEclipse Persistence Tools
 */

public class MonitorPingRegion implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorPingRegionGroup monitorPingRegionGroup;
	private MonitorAlertPolicy monitorAlertPolicy;
	private String name;
	private String note;

	// Constructors

	/** default constructor */
	public MonitorPingRegion() {
	}

	/** minimal constructor */
	public MonitorPingRegion(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public MonitorPingRegion(Integer id,
			MonitorPingRegionGroup monitorPingRegionGroup,
			MonitorAlertPolicy monitorAlertPolicy, String name, String note) {
		this.id = id;
		this.monitorPingRegionGroup = monitorPingRegionGroup;
		this.monitorAlertPolicy = monitorAlertPolicy;
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

	public MonitorPingRegionGroup getMonitorPingRegionGroup() {
		return this.monitorPingRegionGroup;
	}

	public void setMonitorPingRegionGroup(
			MonitorPingRegionGroup monitorPingRegionGroup) {
		this.monitorPingRegionGroup = monitorPingRegionGroup;
	}

	public MonitorAlertPolicy getMonitorAlertPolicy() {
		return this.monitorAlertPolicy;
	}

	public void setMonitorAlertPolicy(MonitorAlertPolicy monitorAlertPolicy) {
		this.monitorAlertPolicy = monitorAlertPolicy;
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