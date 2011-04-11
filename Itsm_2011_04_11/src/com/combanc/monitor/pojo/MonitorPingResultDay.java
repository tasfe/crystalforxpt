package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorPingResultDay entity. @author MyEclipse Persistence Tools
 */

public class MonitorPingResultDay implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorPingDest monitorPingDest;
	private Integer successCount;
	private Integer sendCount;
	private Integer minReplyTime;
	private Integer maxReplyTime;
	private Integer sumReplyTime;
	private Integer sumTtl;
	private Integer responseTime;
	private Integer noResponseTime;
	private Timestamp completedTime;

	// Constructors

	/** default constructor */
	public MonitorPingResultDay() {
	}

	/** minimal constructor */
	public MonitorPingResultDay(Timestamp completedTime) {
		this.completedTime = completedTime;
	}

	/** full constructor */
	public MonitorPingResultDay(MonitorPingDest monitorPingDest,
			Integer successCount, Integer sendCount, Integer minReplyTime,
			Integer maxReplyTime, Integer sumReplyTime, Integer sumTtl,
			Integer responseTime, Integer noResponseTime,
			Timestamp completedTime) {
		this.monitorPingDest = monitorPingDest;
		this.successCount = successCount;
		this.sendCount = sendCount;
		this.minReplyTime = minReplyTime;
		this.maxReplyTime = maxReplyTime;
		this.sumReplyTime = sumReplyTime;
		this.sumTtl = sumTtl;
		this.responseTime = responseTime;
		this.noResponseTime = noResponseTime;
		this.completedTime = completedTime;
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

	public Integer getSuccessCount() {
		return this.successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getSendCount() {
		return this.sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public Integer getMinReplyTime() {
		return this.minReplyTime;
	}

	public void setMinReplyTime(Integer minReplyTime) {
		this.minReplyTime = minReplyTime;
	}

	public Integer getMaxReplyTime() {
		return this.maxReplyTime;
	}

	public void setMaxReplyTime(Integer maxReplyTime) {
		this.maxReplyTime = maxReplyTime;
	}

	public Integer getSumReplyTime() {
		return this.sumReplyTime;
	}

	public void setSumReplyTime(Integer sumReplyTime) {
		this.sumReplyTime = sumReplyTime;
	}

	public Integer getSumTtl() {
		return this.sumTtl;
	}

	public void setSumTtl(Integer sumTtl) {
		this.sumTtl = sumTtl;
	}

	public Integer getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(Integer responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getNoResponseTime() {
		return this.noResponseTime;
	}

	public void setNoResponseTime(Integer noResponseTime) {
		this.noResponseTime = noResponseTime;
	}

	public Timestamp getCompletedTime() {
		return this.completedTime;
	}

	public void setCompletedTime(Timestamp completedTime) {
		this.completedTime = completedTime;
	}

}