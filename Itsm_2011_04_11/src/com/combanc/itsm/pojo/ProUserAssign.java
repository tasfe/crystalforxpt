package com.combanc.itsm.pojo;

/**
 * ProUserAssign entity. @author MyEclipse Persistence Tools
 */

public class ProUserAssign implements java.io.Serializable {

	// Fields

	private Integer assignId;
	private String deployId;
	private String activityName;
	private String roleId;
	private String roleName;
	private String userId;
	private String username;
	private Integer isSigned;

	// Constructors

	/** default constructor */
	public ProUserAssign() {
	}

	/** full constructor */
	public ProUserAssign(String deployId, String activityName, String roleId,
			String roleName, String userId, String username, Integer isSigned) {
		this.deployId = deployId;
		this.activityName = activityName;
		this.roleId = roleId;
		this.roleName = roleName;
		this.userId = userId;
		this.username = username;
		this.isSigned = isSigned;
	}

	// Property accessors

	public Integer getAssignId() {
		return this.assignId;
	}

	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}

	public String getDeployId() {
		return this.deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getIsSigned() {
		return this.isSigned;
	}

	public void setIsSigned(Integer isSigned) {
		this.isSigned = isSigned;
	}

}