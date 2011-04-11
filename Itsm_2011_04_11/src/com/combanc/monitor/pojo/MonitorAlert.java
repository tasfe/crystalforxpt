package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorAlert entity. @author MyEclipse Persistence Tools
 */

public class MonitorAlert implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorAlertSmalltype monitorAlertSmalltype;
	private MonitorAlertType monitorAlertType;
	private Integer file;
	private String old;
	private String value;
	private String limen;
	private String ip;
	private String mac;
	private String uplink;
	private String port;
	private String interface_;
	private String description;
	private String hostName;
	private String compName;
	private String domain;
	private String loginName;
	private Timestamp firstTime;
	private Timestamp lastTime;
	private Integer count = 1;
	private Integer objectId = 0;//对象Id
	// Constructors

	/** default constructor */
	public MonitorAlert() {
	}

	/** minimal constructor */
	public MonitorAlert(Timestamp firstTime, Timestamp lastTime) {
		this.firstTime = firstTime;
		this.lastTime = lastTime;
	}

	/** full constructor */
	public MonitorAlert(MonitorAlertSmalltype monitorAlertSmalltype,
			MonitorAlertType monitorAlertType, Integer file, String old,
			String value, String limen, String ip, String mac, String uplink,
			String port, String interface_, String description,
			String hostName, String compName, String domain, String loginName,
			Timestamp firstTime, Timestamp lastTime) {
		this.monitorAlertSmalltype = monitorAlertSmalltype;
		this.monitorAlertType = monitorAlertType;
		this.file = file;
		this.old = old;
		this.value = value;
		this.limen = limen;
		this.ip = ip;
		this.mac = mac;
		this.uplink = uplink;
		this.port = port;
		this.interface_ = interface_;
		this.description = description;
		this.hostName = hostName;
		this.compName = compName;
		this.domain = domain;
		this.loginName = loginName;
		this.firstTime = firstTime;
		this.lastTime = lastTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorAlertSmalltype getMonitorAlertSmalltype() {
		return this.monitorAlertSmalltype;
	}

	public void setMonitorAlertSmalltype(
			MonitorAlertSmalltype monitorAlertSmalltype) {
		this.monitorAlertSmalltype = monitorAlertSmalltype;
	}

	public MonitorAlertType getMonitorAlertType() {
		return this.monitorAlertType;
	}

	public void setMonitorAlertType(MonitorAlertType monitorAlertType) {
		this.monitorAlertType = monitorAlertType;
	}

	public Integer getFile() {
		return this.file;
	}

	public void setFile(Integer file) {
		this.file = file;
	}

	public String getOld() {
		return this.old;
	}

	public void setOld(String old) {
		this.old = old;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLimen() {
		return this.limen;
	}

	public void setLimen(String limen) {
		this.limen = limen;
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

	public String getUplink() {
		return this.uplink;
	}

	public void setUplink(String uplink) {
		this.uplink = uplink;
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

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
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

	public Timestamp getFirstTime() {
		return this.firstTime;
	}

	public void setFirstTime(Timestamp firstTime) {
		this.firstTime = firstTime;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

 

	 

}