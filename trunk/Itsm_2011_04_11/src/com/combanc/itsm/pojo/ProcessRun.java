package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * ProcessRun entity. @author MyEclipse Persistence Tools
 */

public class ProcessRun implements java.io.Serializable {

	public static final int RUN_STATUS_INIT = 0;

	public static final int RUN_STATUS_RUNNING = 1;

	public static final int RUN_STATUS_FINISHED = 2;
	// Fields

	private Integer runId;
	private ProDefinition proDefinition;
	private Users users;
	private String subject;
	private String creator;
	private String piId;
	private Timestamp createtime;
	private Integer runStatus;

	// Constructors

	/** default constructor */
	public ProcessRun() {
	}

	/** minimal constructor */
	public ProcessRun(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** full constructor */
	public ProcessRun(ProDefinition proDefinition, Users users, String subject,
			String creator, String piId, Timestamp createtime, Integer runStatus) {
		this.proDefinition = proDefinition;
		this.users = users;
		this.subject = subject;
		this.creator = creator;
		this.piId = piId;
		this.createtime = createtime;
		this.runStatus = runStatus;
	}

	// Property accessors

	public Integer getRunId() {
		return this.runId;
	}

	public void setRunId(Integer runId) {
		this.runId = runId;
	}

	public ProDefinition getProDefinition() {
		return this.proDefinition;
	}

	public void setProDefinition(ProDefinition proDefinition) {
		this.proDefinition = proDefinition;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPiId() {
		return this.piId;
	}

	public void setPiId(String piId) {
		this.piId = piId;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getRunStatus() {
		return this.runStatus;
	}

	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}

}