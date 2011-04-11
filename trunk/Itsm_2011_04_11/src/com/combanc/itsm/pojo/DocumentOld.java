package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * DocumentOld entity. @author MyEclipse Persistence Tools
 */

public class DocumentOld implements java.io.Serializable {

	// Fields

	private Integer id;
	private String summary;
	private String context;
	private Timestamp submitTime;
	private String title;
	private String keyword;
	private Integer version;
	private Integer userId;
	private Integer departmentId;
	private String userName;
	private String departmentName;
	private String versionChain;
	private Timestamp createTime;
	private Integer catId;
	private String num;

	// Constructors

	/** default constructor */
	public DocumentOld() {
	}

	/** minimal constructor */
	public DocumentOld(Integer version, String num) {
		this.version = version;
		this.num = num;
	}

	/** full constructor */
	public DocumentOld(String summary, String context, Timestamp submitTime,
			String title, String keyword, Integer version, Integer userId,
			Integer departmentId, String userName, String departmentName,
			String versionChain, Timestamp createTime, Integer catId, String num) {
		this.summary = summary;
		this.context = context;
		this.submitTime = submitTime;
		this.title = title;
		this.keyword = keyword;
		this.version = version;
		this.userId = userId;
		this.departmentId = departmentId;
		this.userName = userName;
		this.departmentName = departmentName;
		this.versionChain = versionChain;
		this.createTime = createTime;
		this.catId = catId;
		this.num = num;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Timestamp getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getVersionChain() {
		return this.versionChain;
	}

	public void setVersionChain(String versionChain) {
		this.versionChain = versionChain;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}