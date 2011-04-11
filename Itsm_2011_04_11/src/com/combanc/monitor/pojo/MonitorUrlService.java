package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorUrlService entity. @author MyEclipse Persistence Tools
 */

public class MonitorUrlService implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorDevice monitorDevice;
	private MonitorUrlType monitorUrlType;
	private MonitorUrlResponse monitorUrlResponse;
	private String parameter;
	private String description;
	private Timestamp lastruntime;
	private Integer monitorinterval;
	private String dayofweek;
	private Integer dailyStart;
	private Integer dailyEnd;
	private Integer severity;
	private Integer enabled;
	private Integer state;
	private String lastmsg;
	private Integer descType;
	private String wonteddesc;
	private Integer erratumInterval;
	private Integer erratumCount;
	private String srvName;
	private String stamp;
	private Integer lastState;

	// Constructors

	/** default constructor */
	public MonitorUrlService() {
	}

	/** minimal constructor */
	public MonitorUrlService(Timestamp lastruntime) {
		this.lastruntime = lastruntime;
	}

	/** full constructor */
	public MonitorUrlService(MonitorDevice monitorDevice,
			MonitorUrlType monitorUrlType,
			MonitorUrlResponse monitorUrlResponse, String parameter,
			String description, Timestamp lastruntime, Integer monitorinterval,
			String dayofweek, Integer dailyStart, Integer dailyEnd,
			Integer severity, Integer enabled, Integer state, String lastmsg,
			Integer descType, String wonteddesc, Integer erratumInterval,
			Integer erratumCount, String srvName, String stamp,
			Integer lastState) {
		this.monitorDevice = monitorDevice;
		this.monitorUrlType = monitorUrlType;
		this.monitorUrlResponse = monitorUrlResponse;
		this.parameter = parameter;
		this.description = description;
		this.lastruntime = lastruntime;
		this.monitorinterval = monitorinterval;
		this.dayofweek = dayofweek;
		this.dailyStart = dailyStart;
		this.dailyEnd = dailyEnd;
		this.severity = severity;
		this.enabled = enabled;
		this.state = state;
		this.lastmsg = lastmsg;
		this.descType = descType;
		this.wonteddesc = wonteddesc;
		this.erratumInterval = erratumInterval;
		this.erratumCount = erratumCount;
		this.srvName = srvName;
		this.stamp = stamp;
		this.lastState = lastState;
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

	public MonitorUrlType getMonitorUrlType() {
		return this.monitorUrlType;
	}

	public void setMonitorUrlType(MonitorUrlType monitorUrlType) {
		this.monitorUrlType = monitorUrlType;
	}

	public MonitorUrlResponse getMonitorUrlResponse() {
		return this.monitorUrlResponse;
	}

	public void setMonitorUrlResponse(MonitorUrlResponse monitorUrlResponse) {
		this.monitorUrlResponse = monitorUrlResponse;
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastruntime() {
		return this.lastruntime;
	}

	public void setLastruntime(Timestamp lastruntime) {
		this.lastruntime = lastruntime;
	}

	public Integer getMonitorinterval() {
		return this.monitorinterval;
	}

	public void setMonitorinterval(Integer monitorinterval) {
		this.monitorinterval = monitorinterval;
	}

	public String getDayofweek() {
		return this.dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public Integer getDailyStart() {
		return this.dailyStart;
	}

	public void setDailyStart(Integer dailyStart) {
		this.dailyStart = dailyStart;
	}

	public Integer getDailyEnd() {
		return this.dailyEnd;
	}

	public void setDailyEnd(Integer dailyEnd) {
		this.dailyEnd = dailyEnd;
	}

	public Integer getSeverity() {
		return this.severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getLastmsg() {
		return this.lastmsg;
	}

	public void setLastmsg(String lastmsg) {
		this.lastmsg = lastmsg;
	}

	public Integer getDescType() {
		return this.descType;
	}

	public void setDescType(Integer descType) {
		this.descType = descType;
	}

	public String getWonteddesc() {
		return this.wonteddesc;
	}

	public void setWonteddesc(String wonteddesc) {
		this.wonteddesc = wonteddesc;
	}

	public Integer getErratumInterval() {
		return this.erratumInterval;
	}

	public void setErratumInterval(Integer erratumInterval) {
		this.erratumInterval = erratumInterval;
	}

	public Integer getErratumCount() {
		return this.erratumCount;
	}

	public void setErratumCount(Integer erratumCount) {
		this.erratumCount = erratumCount;
	}

	public String getSrvName() {
		return this.srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getStamp() {
		return this.stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public Integer getLastState() {
		return this.lastState;
	}

	public void setLastState(Integer lastState) {
		this.lastState = lastState;
	}

}