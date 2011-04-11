package com.combanc.monitor.pojo;

/**
 * MonitorUser entity. @author MyEclipse Persistence Tools
 */

public class MonitorUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorDepartment monitorDepartment;
	private String userName;
	private String fullName;
	private String password;
	private String email;
	private String phone;
	private String mobile;
	private Integer status;

	// Constructors

	/** default constructor */
	public MonitorUser() {
	}

	/** full constructor */
	public MonitorUser(MonitorDepartment monitorDepartment, String userName,
			String fullName, String password, String email, String phone,
			String mobile, Integer status) {
		this.monitorDepartment = monitorDepartment;
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorDepartment getMonitorDepartment() {
		return this.monitorDepartment;
	}

	public void setMonitorDepartment(MonitorDepartment monitorDepartment) {
		this.monitorDepartment = monitorDepartment;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}