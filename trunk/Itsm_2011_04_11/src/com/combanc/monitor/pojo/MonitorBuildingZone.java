package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorBuildingZone entity. @author MyEclipse Persistence Tools
 */

public class MonitorBuildingZone implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorBuilding monitorBuilding;
	private String name;
	private String note;
	private Set monitorMacInfos = new HashSet(0);
	private Set monitorDevices = new HashSet(0);
	private Set monitorZoneVlans = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorBuildingZone() {
	}

	/** full constructor */
	public MonitorBuildingZone(MonitorBuilding monitorBuilding, String name,
			String note, Set monitorMacInfos, Set monitorDevices,
			Set monitorZoneVlans) {
		this.monitorBuilding = monitorBuilding;
		this.name = name;
		this.note = note;
		this.monitorMacInfos = monitorMacInfos;
		this.monitorDevices = monitorDevices;
		this.monitorZoneVlans = monitorZoneVlans;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorBuilding getMonitorBuilding() {
		return this.monitorBuilding;
	}

	public void setMonitorBuilding(MonitorBuilding monitorBuilding) {
		this.monitorBuilding = monitorBuilding;
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

	public Set getMonitorMacInfos() {
		return this.monitorMacInfos;
	}

	public void setMonitorMacInfos(Set monitorMacInfos) {
		this.monitorMacInfos = monitorMacInfos;
	}

	public Set getMonitorDevices() {
		return this.monitorDevices;
	}

	public void setMonitorDevices(Set monitorDevices) {
		this.monitorDevices = monitorDevices;
	}

	public Set getMonitorZoneVlans() {
		return this.monitorZoneVlans;
	}

	public void setMonitorZoneVlans(Set monitorZoneVlans) {
		this.monitorZoneVlans = monitorZoneVlans;
	}

}