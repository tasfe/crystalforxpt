package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorBuilding entity. @author MyEclipse Persistence Tools
 */

public class MonitorBuilding implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String note;
	private Set monitorMacInfos = new HashSet(0);
	private Set monitorBuildingZones = new HashSet(0);
	private Set monitorDevices = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorBuilding() {
	}

	/** full constructor */
	public MonitorBuilding(String name, String note, Set monitorMacInfos,
			Set monitorBuildingZones, Set monitorDevices) {
		this.name = name;
		this.note = note;
		this.monitorMacInfos = monitorMacInfos;
		this.monitorBuildingZones = monitorBuildingZones;
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

	public Set getMonitorBuildingZones() {
		return this.monitorBuildingZones;
	}

	public void setMonitorBuildingZones(Set monitorBuildingZones) {
		this.monitorBuildingZones = monitorBuildingZones;
	}

	public Set getMonitorDevices() {
		return this.monitorDevices;
	}

	public void setMonitorDevices(Set monitorDevices) {
		this.monitorDevices = monitorDevices;
	}

}