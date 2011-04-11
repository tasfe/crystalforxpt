package com.combanc.itsm.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * SchedualedTaskDetail entity. @author MyEclipse Persistence Tools
 */

public class SchedualedTaskDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private SchedualedTasks schedualedTasks;
	private String engineer;
	private String taskCode;
	private String state;
	private Timestamp schedualedTime;
	private Timestamp finishedTime;
	private String solution;
	private String advice;
	private Timestamp reviewTime;
	private Integer serviceLvl;
	private Integer sarLvl;
	private String finiEngineer;
	private String cause;
	private String reviewEngi;
	private String members;
	private String cronExpress;
	private Set schedualedTaskUsers = new HashSet();

	// Constructors

	/** default constructor */
	public SchedualedTaskDetail() {
	}

	/** minimal constructor */
	public SchedualedTaskDetail(SchedualedTasks schedualedTasks) {
		this.schedualedTasks = schedualedTasks;
	}

	/** full constructor */
	public SchedualedTaskDetail(SchedualedTasks schedualedTasks,
			String engineer, String taskCode, String state,
			Timestamp schedualedTime, Timestamp finishedTime, String solution,
			String advice, Timestamp reviewTime, Integer serviceLvl,
			Integer sarLvl) {
		this.schedualedTasks = schedualedTasks;
		this.engineer = engineer;
		this.taskCode = taskCode;
		this.state = state;
		this.schedualedTime = schedualedTime;
		this.finishedTime = finishedTime;
		this.solution = solution;
		this.advice = advice;
		this.reviewTime = reviewTime;
		this.serviceLvl = serviceLvl;
		this.sarLvl = sarLvl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SchedualedTasks getSchedualedTasks() {
		return this.schedualedTasks;
	}

	public void setSchedualedTasks(SchedualedTasks schedualedTasks) {
		this.schedualedTasks = schedualedTasks;
	}

	public String getEngineer() {
		return this.engineer;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}

	public String getTaskCode() {
		return this.taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getSchedualedTime() {
		return this.schedualedTime;
	}

	public void setSchedualedTime(Timestamp schedualedTime) {
		this.schedualedTime = schedualedTime;
	}

	public Timestamp getFinishedTime() {
		return this.finishedTime;
	}

	public void setFinishedTime(Timestamp finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Timestamp getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Integer getServiceLvl() {
		return this.serviceLvl;
	}

	public void setServiceLvl(Integer serviceLvl) {
		this.serviceLvl = serviceLvl;
	}

	public Integer getSarLvl() {
		return this.sarLvl;
	}

	public void setSarLvl(Integer sarLvl) {
		this.sarLvl = sarLvl;
	}

	public String getFiniEngineer() {
		return finiEngineer;
	}

	public void setFiniEngineer(String finiEngineer) {
		this.finiEngineer = finiEngineer;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getReviewEngi() {
		return reviewEngi;
	}

	public void setReviewEngi(String reviewEngi) {
		this.reviewEngi = reviewEngi;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public String getCronExpress() {
		return cronExpress;
	}

	public void setCronExpress(String cronExpress) {
		this.cronExpress = cronExpress;
	}

	public Set getSchedualedTaskUsers() {
		return schedualedTaskUsers;
	}

	public void setSchedualedTaskUsers(Set schedualedTaskUsers) {
		this.schedualedTaskUsers = schedualedTaskUsers;
	}

}