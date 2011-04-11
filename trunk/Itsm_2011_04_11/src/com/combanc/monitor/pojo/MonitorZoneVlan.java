package com.combanc.monitor.pojo;

/**
 * MonitorZoneVlan entity. @author MyEclipse Persistence Tools
 */

public class MonitorZoneVlan implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorBuildingZone monitorBuildingZone;
	private MonitorVlan monitorVlan;

	// Constructors

	/** default constructor */
	public MonitorZoneVlan() {
	}

	/** full constructor */
	public MonitorZoneVlan(MonitorBuildingZone monitorBuildingZone,
			MonitorVlan monitorVlan) {
		this.monitorBuildingZone = monitorBuildingZone;
		this.monitorVlan = monitorVlan;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorBuildingZone getMonitorBuildingZone() {
		return this.monitorBuildingZone;
	}

	public void setMonitorBuildingZone(MonitorBuildingZone monitorBuildingZone) {
		this.monitorBuildingZone = monitorBuildingZone;
	}

	public MonitorVlan getMonitorVlan() {
		return this.monitorVlan;
	}

	public void setMonitorVlan(MonitorVlan monitorVlan) {
		this.monitorVlan = monitorVlan;
	}

}