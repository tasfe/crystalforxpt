package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorVlan entity. @author MyEclipse Persistence Tools
 */

public class MonitorVlan implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorDevice monitorDevice;
	private String segment;
	private String mask;
	private String description;
	private Set monitorZoneVlans = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorVlan() {
	}

	/** full constructor */
	public MonitorVlan(MonitorDevice monitorDevice, String segment,
			String mask, String description, Set monitorZoneVlans) {
		this.monitorDevice = monitorDevice;
		this.segment = segment;
		this.mask = mask;
		this.description = description;
		this.monitorZoneVlans = monitorZoneVlans;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorDevice getMonitorDevice() {
		return this.monitorDevice;
	}

	public void setMonitorDevice(MonitorDevice monitorDevice) {
		this.monitorDevice = monitorDevice;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getMask() {
		return this.mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getMonitorZoneVlans() {
		return this.monitorZoneVlans;
	}

	public void setMonitorZoneVlans(Set monitorZoneVlans) {
		this.monitorZoneVlans = monitorZoneVlans;
	}

}