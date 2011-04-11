package com.combanc.monitor.pojo;

/**
 * MonitorInterfaceCache entity. @author MyEclipse Persistence Tools
 */

public class MonitorInterfaceCache implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorDevice monitorDevice;
	private String port;
	private String interface_;
	private String description;
	private String note;
	private String llocate;
	private String user;
	private String note1;
	private String note2;
	private String note3;
	private String note4;

	// Constructors

	/** default constructor */
	public MonitorInterfaceCache() {
	}

	/** full constructor */
	public MonitorInterfaceCache(MonitorDevice monitorDevice, String port,
			String interface_, String description, String note, String llocate,
			String user, String note1, String note2, String note3, String note4) {
		this.monitorDevice = monitorDevice;
		this.port = port;
		this.interface_ = interface_;
		this.description = description;
		this.note = note;
		this.llocate = llocate;
		this.user = user;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
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

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getInterface_() {
		return this.interface_;
	}

	public void setInterface_(String interface_) {
		this.interface_ = interface_;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLlocate() {
		return this.llocate;
	}

	public void setLlocate(String llocate) {
		this.llocate = llocate;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return this.note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return this.note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

}