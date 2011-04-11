package com.combanc.itsm.pojo;

import java.util.Date;

/**
 * ServiceLevel entity. @author MyEclipse Persistence Tools
 */

public class ServiceLevel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer departId;
	private String title;
	private Integer requestNo;
	private Integer engineerId;
	private String case_;
	private Date startTime;
	private Date endTime;
	private Integer delayTime;
	private Integer totleTime;
	private String plan;

	// Constructors

	/** default constructor */
	public ServiceLevel() {
	}

	/** minimal constructor */
	public ServiceLevel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ServiceLevel(Integer id, Integer departId, String title,
			Integer requestNo, Integer engineerId, String case_,
			Date startTime, Date endTime, Integer delayTime, Integer totleTime,
			String plan) {
		this.id = id;
		this.departId = departId;
		this.title = title;
		this.requestNo = requestNo;
		this.engineerId = engineerId;
		this.case_ = case_;
		this.startTime = startTime;
		this.endTime = endTime;
		this.delayTime = delayTime;
		this.totleTime = totleTime;
		this.plan = plan;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartId() {
		return this.departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getRequestNo() {
		return this.requestNo;
	}

	public void setRequestNo(Integer requestNo) {
		this.requestNo = requestNo;
	}

	public Integer getEngineerId() {
		return this.engineerId;
	}

	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}

	public String getCase_() {
		return this.case_;
	}

	public void setCase_(String case_) {
		this.case_ = case_;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDelayTime() {
		return this.delayTime;
	}

	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}

	public Integer getTotleTime() {
		return this.totleTime;
	}

	public void setTotleTime(Integer totleTime) {
		this.totleTime = totleTime;
	}

	public String getPlan() {
		return this.plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

}