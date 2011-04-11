package com.combanc.itsm.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * SchedualedTaskDetail entity. @author MyEclipse Persistence Tools
 */

public class Schedualed implements java.io.Serializable {

	private String type;
	private Integer distance;
	private String distanceType;
	private String time;
	private String yearStart;
	private String yearEnd;
	private String monthStart;
	private String montEnd;
	private String dayStart;
	private String dayEnd;
	private String cronExpress;
	private String week;
	
	/** default constructor */
	public Schedualed() {
	}

	/** full constructor */
	public Schedualed(String type, Integer distance, String distanceType,String time,
			String yearStart,String yearEnd,String monthStart,String montEnd,
			String dayStart,String dayEnd,String cronExpress,String week) {
		this.type = type;
		this.distance = distance;
		this.distanceType = distanceType;
		this.time = time;
		this.yearStart = yearStart;
		this.yearEnd = yearEnd;
		this.monthStart = monthStart;
		this.montEnd = montEnd;
		this.dayStart = dayStart;
		this.dayEnd = dayEnd;
		this.cronExpress = cronExpress;
		this.week = week;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getDistanceType() {
		return distanceType;
	}

	public void setDistanceType(String distanceType) {
		this.distanceType = distanceType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getYearStart() {
		return yearStart;
	}

	public void setYearStart(String yearStart) {
		this.yearStart = yearStart;
	}

	public String getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(String yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getMonthStart() {
		return monthStart;
	}

	public void setMonthStart(String monthStart) {
		this.monthStart = monthStart;
	}

	public String getMontEnd() {
		return montEnd;
	}

	public void setMontEnd(String montEnd) {
		this.montEnd = montEnd;
	}

	public String getDayStart() {
		return dayStart;
	}

	public void setDayStart(String dayStart) {
		this.dayStart = dayStart;
	}

	public String getDayEnd() {
		return dayEnd;
	}

	public void setDayEnd(String dayEnd) {
		this.dayEnd = dayEnd;
	}

	public String getCronExpress() {
		return cronExpress;
	}

	public void setCronExpress(String cronExpress) {
		this.cronExpress = cronExpress;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

}