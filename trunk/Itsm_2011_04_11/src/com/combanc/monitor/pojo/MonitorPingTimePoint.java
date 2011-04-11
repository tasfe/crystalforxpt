package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorPingTimePoint entity. @author MyEclipse Persistence Tools
 */

public class MonitorPingTimePoint implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp timePoint;

	// Constructors

	/** default constructor */
	public MonitorPingTimePoint() {
	}

	/** full constructor */
	public MonitorPingTimePoint(Timestamp timePoint) {
		this.timePoint = timePoint;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTimePoint() {
		return this.timePoint;
	}

	public void setTimePoint(Timestamp timePoint) {
		this.timePoint = timePoint;
	}

}