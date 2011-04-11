package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorDepartment entity. @author MyEclipse Persistence Tools
 */

public class MonitorDepartment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer departmentId;
	private String name;
	private String leader;
	private String tel;
	private String email;
	private String url;
	private String note;
	private Set monitorMacInfos = new HashSet(0);
	private Set monitorUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorDepartment() {
	}

	/** full constructor */
	public MonitorDepartment(Integer departmentId, String name, String leader,
			String tel, String email, String url, String note,
			Set monitorMacInfos, Set monitorUsers) {
		this.departmentId = departmentId;
		this.name = name;
		this.leader = leader;
		this.tel = tel;
		this.email = email;
		this.url = url;
		this.note = note;
		this.monitorMacInfos = monitorMacInfos;
		this.monitorUsers = monitorUsers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getMonitorMacInfos() {
		return this.monitorMacInfos;
	}

	public void setMonitorMacInfos(Set monitorMacInfos) {
		this.monitorMacInfos = monitorMacInfos;
	}

	public Set getMonitorUsers() {
		return this.monitorUsers;
	}

	public void setMonitorUsers(Set monitorUsers) {
		this.monitorUsers = monitorUsers;
	}

}