package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * ProjectManage entity. @author MyEclipse Persistence Tools
 */

public class ProjectManage implements java.io.Serializable {

	// Fields

	private Long id;
	private String projectName;
	private String projectManager;
	private String projectLocation;
	private Integer projectType;
	private Timestamp beginTime;
	private Timestamp endTime;
	private String projectContent;
	private String file;
	private Long userId;
	private String userName;
	private Long projectNo;
	private String projectCreater;

	// Constructors

	/** default constructor */
	public ProjectManage() {
	}

	/** full constructor */
	public ProjectManage(String projectName, String projectManager,
			String projectLocation, Integer projectType, Timestamp beginTime,
			Timestamp endTime, String projectContent, String file, Long userId,
			String userName, Long projectNo, String projectCreater) {
		this.projectName = projectName;
		this.projectManager = projectManager;
		this.projectLocation = projectLocation;
		this.projectType = projectType;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.projectContent = projectContent;
		this.file = file;
		this.userId = userId;
		this.userName = userName;
		this.projectNo = projectNo;
		this.projectCreater = projectCreater;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectManager() {
		return this.projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getProjectLocation() {
		return this.projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public Integer getProjectType() {
		return this.projectType;
	}

	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getProjectContent() {
		return this.projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(Long projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectCreater() {
		return this.projectCreater;
	}

	public void setProjectCreater(String projectCreater) {
		this.projectCreater = projectCreater;
	}

}