package com.combanc.monitor.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.combanc.monitor.constants.MainConstants;

/**
 * MonitorAlertPolicy entity. @author MyEclipse Persistence Tools
 */

public class MonitorAlertPolicy implements java.io.Serializable,Cloneable  {

	// Fields

	private Integer id;
	private String name;
	private Timestamp modifyTime;
	private String note;
	private Integer checkGap;
	private Integer errorGap;
	private Integer errorRetry;
	private Integer limenRetry;
	private Integer tempRetry;
	private String dayOfWeek;
	private Integer dailyStart;
	private Integer dailyEnd;
	private Boolean mobileSwitch;
	private Boolean emailSwitch;
	private Boolean soundSwitch;
	private String alertReceivers;
	private String alertTypes;
	private Set monitorDevices = new HashSet(0);
	
	

	// Constructors

	/** default constructor */
	public MonitorAlertPolicy() {
		errorRetry = MainConstants.DEFAULT_ERROR_RETRY_COUNT;
		limenRetry = MainConstants.DEFAULT_LIMEN_RETRY_COUNT;
		tempRetry = MainConstants.DEFAULT_TEMP_RETRY_COUNT;
		note = "";
		mobileSwitch = true;
		emailSwitch = true;
		soundSwitch = true;
		alertReceivers = "";
		alertTypes = "";
		
	}

	/** minimal constructor */
	public MonitorAlertPolicy(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public MonitorAlertPolicy(String name, Timestamp modifyTime, String note,
			Integer checkGap, Integer errorGap, Integer errorRetry,
			Integer limenRetry, Integer tempRetry, String dayOfWeek,
			Integer dailyStart, Integer dailyEnd, Boolean mobileSwitch,
			Boolean emailSwitch, Boolean soundSwitch, String alertReceivers,
			String alertTypes, Set monitorDevices) {
		this.name = name;
		this.modifyTime = modifyTime;
		this.note = note;
		this.checkGap = checkGap;
		this.errorGap = errorGap;
		this.errorRetry = errorRetry;
		this.limenRetry = limenRetry;
		this.tempRetry = tempRetry;
		this.dayOfWeek = dayOfWeek;
		this.dailyStart = dailyStart;
		this.dailyEnd = dailyEnd;
		this.mobileSwitch = mobileSwitch;
		this.emailSwitch = emailSwitch;
		this.soundSwitch = soundSwitch;
		this.alertReceivers = alertReceivers;
		this.alertTypes = alertTypes;
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

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getCheckGap() {
		return this.checkGap;
	}

	public void setCheckGap(Integer checkGap) {
		this.checkGap = checkGap;
	}

	public Integer getErrorGap() {
		return this.errorGap;
	}

	public void setErrorGap(Integer errorGap) {
		this.errorGap = errorGap;
	}

	public Integer getErrorRetry() {
		return this.errorRetry;
	}

	public void setErrorRetry(Integer errorRetry) {
		this.errorRetry = errorRetry;
	}

	public Integer getLimenRetry() {
		return this.limenRetry;
	}

	public void setLimenRetry(Integer limenRetry) {
		this.limenRetry = limenRetry;
	}

	public Integer getTempRetry() {
		return this.tempRetry;
	}

	public void setTempRetry(Integer tempRetry) {
		this.tempRetry = tempRetry;
	}

	public String getDayOfWeek() {
		return this.dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
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

	public Boolean getMobileSwitch() {
		return this.mobileSwitch;
	}

	public void setMobileSwitch(Boolean mobileSwitch) {
		this.mobileSwitch = mobileSwitch;
	}

	public Boolean getEmailSwitch() {
		return this.emailSwitch;
	}

	public void setEmailSwitch(Boolean emailSwitch) {
		this.emailSwitch = emailSwitch;
	}

	public Boolean getSoundSwitch() {
		return this.soundSwitch;
	}

	public void setSoundSwitch(Boolean soundSwitch) {
		this.soundSwitch = soundSwitch;
	}

	public String getAlertReceivers() {
		return this.alertReceivers;
	}

	public void setAlertReceivers(String alertReceivers) {
		this.alertReceivers = alertReceivers;
	}

	public String getAlertTypes() {
		return this.alertTypes;
	}

	public void setAlertTypes(String alertTypes) {
		this.alertTypes = alertTypes;
	}

	public Set getMonitorDevices() {
		return this.monitorDevices;
	}

	public void setMonitorDevices(Set monitorDevices) {
		this.monitorDevices = monitorDevices;
	}

	public Object clone() throws CloneNotSupportedException {
		MonitorAlertPolicy policy =  (MonitorAlertPolicy) super.clone();
		policy.setMonitorDevices(null);
        return  policy;
    }
	

}