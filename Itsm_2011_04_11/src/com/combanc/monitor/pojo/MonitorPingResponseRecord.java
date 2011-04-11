package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorPingResponseRecord entity. @author MyEclipse Persistence Tools
 */

public class MonitorPingResponseRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorPingDest monitorPingDest;
	private Integer excuteCount;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer responseStatus;

	// Constructors

	/** default constructor */
	public MonitorPingResponseRecord() {
	}

	/** minimal constructor */
	public MonitorPingResponseRecord(Timestamp startTime, Timestamp endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/** full constructor */
	public MonitorPingResponseRecord(MonitorPingDest monitorPingDest,
			Integer excuteCount, Timestamp startTime, Timestamp endTime,
			Integer responseStatus) {
		this.monitorPingDest = monitorPingDest;
		this.excuteCount = excuteCount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.responseStatus = responseStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorPingDest getMonitorPingDest() {
		return this.monitorPingDest;
	}

	public void setMonitorPingDest(MonitorPingDest monitorPingDest) {
		this.monitorPingDest = monitorPingDest;
	}

	public Integer getExcuteCount() {
		return this.excuteCount;
	}

	public void setExcuteCount(Integer excuteCount) {
		this.excuteCount = excuteCount;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getResponseStatus() {
		return this.responseStatus;
	}

	public void setResponseStatus(Integer responseStatus) {
		this.responseStatus = responseStatus;
	}

}