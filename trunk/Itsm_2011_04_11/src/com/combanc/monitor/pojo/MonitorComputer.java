package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorComputer entity. @author MyEclipse Persistence Tools
 */

public class MonitorComputer implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorDevice monitorDevice;
	private String ip;
	private String mac;
	private String devicePort;
	private String deviceInterface;
	private String interfaceDescription;
	private String hostName;
	private String computerName;
	private String domain;
	private String loginName;
	private String segment;
	private Timestamp discoveryTime;
	private String user;
	private String place;
	private String department;
	private Boolean registered = false;
	private Boolean snapshot = false;
	private String note1;
	private String note2;
	private String note3;
	private String note4;

	// Constructors

	/** default constructor */
	public MonitorComputer() {
	}

	/** minimal constructor */
	public MonitorComputer(MonitorDevice monitorDevice, Timestamp discoveryTime) {
		this.monitorDevice = monitorDevice;
		this.discoveryTime = discoveryTime;
	}

	/** full constructor */
	public MonitorComputer(MonitorDevice monitorDevice, String ip, String mac,
			String devicePort, String deviceInterface, String interfaceDescription,
			String hostName, String computerName, String domain, String loginName,
			String segment, Timestamp discoveryTime, String user, String place,
			String department, Boolean registered, Boolean snapshot, String note1, String note2, String note3,
			String note4) {
		this.monitorDevice = monitorDevice;
		this.ip = ip;
		this.mac = mac;
		this.devicePort = devicePort;
		this.deviceInterface = deviceInterface;
		this.interfaceDescription = interfaceDescription;
		this.hostName = hostName;
		this.computerName = computerName;
		this.domain = domain;
		this.loginName = loginName;
		this.segment = segment;
		this.discoveryTime = discoveryTime;
		this.user = user;
		this.place = place;
		this.department = department;
		this.registered = registered;
		this.snapshot = snapshot;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDevicePort() {
		return this.devicePort;
	}

	public void setDevicePort(String devicePort) {
		this.devicePort = devicePort;
	}

	public String getDeviceInterface() {
		return this.deviceInterface;
	}

	public void setDeviceInterface(String deviceInterface) {
		this.deviceInterface = deviceInterface;
	}

	public String getInterfaceDescription() {
		return this.interfaceDescription;
	}

	public void setInterfaceDescription(String interfaceDescription) {
		this.interfaceDescription = interfaceDescription;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getComputerName() {
		return computerName;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public Timestamp getDiscoveryTime() {
		return this.discoveryTime;
	}

	public void setDiscoveryTime(Timestamp discoveryTime) {
		this.discoveryTime = discoveryTime;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Boolean getRegistered() {
		return this.registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	public void setSnapshot(Boolean snapshot) {
		this.snapshot = snapshot;
	}

	public Boolean getSnapshot() {
		return snapshot;
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