package com.combanc.monitor.pojo;

/**
 * MonitorVendor entity. @author MyEclipse Persistence Tools
 */

public class MonitorVendor implements java.io.Serializable {

	// Fields

	private String vendor;
	private String cpuOid;
	private String temperature;
	private String note;

	// Constructors

	/** default constructor */
	public MonitorVendor() {
	}

	/** full constructor */
	public MonitorVendor(String cpuOid, String temperature, String note) {
		this.cpuOid = cpuOid;
		this.temperature = temperature;
		this.note = note;
	}

	// Property accessors

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getCpuOid() {
		return this.cpuOid;
	}

	public void setCpuOid(String cpuOid) {
		this.cpuOid = cpuOid;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}