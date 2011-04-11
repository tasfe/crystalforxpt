package com.combanc.monitor.pojo;

/**
 * MonitorServerProcess entity. @author MyEclipse Persistence Tools
 */

public class MonitorServerProcess implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorDevice monitorDevice;
	private String name;
	private Boolean start;
	private String note;

	// Constructors

	/** default constructor */
	public MonitorServerProcess() {
	}

	/** full constructor */
	public MonitorServerProcess(MonitorDevice monitorDevice, String name,
			Boolean start, String note) {
		this.monitorDevice = monitorDevice;
		this.name = name;
		this.start = start;
		this.note = note;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStart() {
		return this.start;
	}

	public void setStart(Boolean start) {
		this.start = start;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}