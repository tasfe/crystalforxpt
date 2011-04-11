package com.combanc.monitor.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * MonitorPingDest entity. @author MyEclipse Persistence Tools
 */

public class MonitorPingDest implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorPingRegion monitorPingRegion;
	private MonitorPingDestType monitorPingDestType;
	private String destIpUrl;
	private String hostName;
	private String location;
	private String ministry;
	private String department;
	private String user;
	private String phone;
	private String mac;
	private String description;
	private String application;
	private String assetsNo;
	private String diskNo;
	private String note;
	private Boolean isRun;
	private Timestamp flushTime;
	private Set monitorPingResults = new HashSet(0);
	private Set monitorPingResultDaies = new HashSet(0);
	private Set monitorPingResponseRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorPingDest() {
	}

	/** minimal constructor */
	public MonitorPingDest(Integer id, Timestamp flushTime) {
		this.id = id;
		this.flushTime = flushTime;
	}

	/** full constructor */
	public MonitorPingDest(Integer id, MonitorPingRegion monitorPingRegion,
			MonitorPingDestType monitorPingDestType, String destIpUrl,
			String hostName, String location, String ministry,
			String department, String user, String phone, String mac,
			String description, String application, String assetsNo,
			String diskNo, String note, Boolean isRun, Timestamp flushTime,
			Set monitorPingResults, Set monitorPingResultDaies,
			Set monitorPingResponseRecords) {
		this.id = id;
		this.monitorPingRegion = monitorPingRegion;
		this.monitorPingDestType = monitorPingDestType;
		this.destIpUrl = destIpUrl;
		this.hostName = hostName;
		this.location = location;
		this.ministry = ministry;
		this.department = department;
		this.user = user;
		this.phone = phone;
		this.mac = mac;
		this.description = description;
		this.application = application;
		this.assetsNo = assetsNo;
		this.diskNo = diskNo;
		this.note = note;
		this.isRun = isRun;
		this.flushTime = flushTime;
		this.monitorPingResults = monitorPingResults;
		this.monitorPingResultDaies = monitorPingResultDaies;
		this.monitorPingResponseRecords = monitorPingResponseRecords;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorPingRegion getMonitorPingRegion() {
		return this.monitorPingRegion;
	}

	public void setMonitorPingRegion(MonitorPingRegion monitorPingRegion) {
		this.monitorPingRegion = monitorPingRegion;
	}

	public MonitorPingDestType getMonitorPingDestType() {
		return this.monitorPingDestType;
	}

	public void setMonitorPingDestType(MonitorPingDestType monitorPingDestType) {
		this.monitorPingDestType = monitorPingDestType;
	}

	public String getDestIpUrl() {
		return this.destIpUrl;
	}

	public void setDestIpUrl(String destIpUrl) {
		this.destIpUrl = destIpUrl;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMinistry() {
		return this.ministry;
	}

	public void setMinistry(String ministry) {
		this.ministry = ministry;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getAssetsNo() {
		return this.assetsNo;
	}

	public void setAssetsNo(String assetsNo) {
		this.assetsNo = assetsNo;
	}

	public String getDiskNo() {
		return this.diskNo;
	}

	public void setDiskNo(String diskNo) {
		this.diskNo = diskNo;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getIsRun() {
		return this.isRun;
	}

	public void setIsRun(Boolean isRun) {
		this.isRun = isRun;
	}

	public Timestamp getFlushTime() {
		return this.flushTime;
	}

	public void setFlushTime(Timestamp flushTime) {
		this.flushTime = flushTime;
	}

	public Set getMonitorPingResults() {
		return this.monitorPingResults;
	}

	public void setMonitorPingResults(Set monitorPingResults) {
		this.monitorPingResults = monitorPingResults;
	}

	public Set getMonitorPingResultDaies() {
		return this.monitorPingResultDaies;
	}

	public void setMonitorPingResultDaies(Set monitorPingResultDaies) {
		this.monitorPingResultDaies = monitorPingResultDaies;
	}

	public Set getMonitorPingResponseRecords() {
		return this.monitorPingResponseRecords;
	}

	public void setMonitorPingResponseRecords(Set monitorPingResponseRecords) {
		this.monitorPingResponseRecords = monitorPingResponseRecords;
	}

}