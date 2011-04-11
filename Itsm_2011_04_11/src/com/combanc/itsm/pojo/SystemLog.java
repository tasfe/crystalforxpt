package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * SystemLog entity. @author MyEclipse Persistence Tools
 */

public class SystemLog implements java.io.Serializable {

	// Fields

	private Long logId;
	private Integer userId;
	private String userName;
	private String loginIp;
	private String operation;
	private Timestamp createDate;

	// Constructors

	/** default constructor */
	public SystemLog() {
	}

	/** minimal constructor */
	public SystemLog(Integer userId, String userName, Timestamp createDate) {
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
	}

	/** full constructor */
	public SystemLog(Integer userId, String userName, String loginIp,
			String operation, Timestamp createDate) {
		this.userId = userId;
		this.userName = userName;
		this.loginIp = loginIp;
		this.operation = operation;
		this.createDate = createDate;
	}

	// Property accessors

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}