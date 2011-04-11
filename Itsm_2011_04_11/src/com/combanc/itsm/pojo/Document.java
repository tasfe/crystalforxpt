package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * Document entity. @author MyEclipse Persistence Tools
 */

public class Document implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Department department;
	private String summary;
	private String context;
	private Timestamp submitTime;
	private String title;
	private String keyword;
	private int version;
	private String userName;
	private String departmentName;
	private String versionChian;
	private Timestamp createTime;
	private DocumentCat cat;
	private String num;
	private String catcode;

	// Constructors

	public String getCatcode() {
		return catcode;
	}



	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}



	/** default constructor */
	public Document() {
	}



	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}



	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}



	/**
	 * @return the cat
	 */
	public DocumentCat getCat() {
		return cat;
	}



	/**
	 * @param cat the cat to set
	 */
	public void setCat(DocumentCat cat) {
		this.cat = cat;
	}



	/**
	 * @return the versionChian
	 */
	public String getVersionChian() {
		return versionChian;
	}



	/**
	 * @param versionChian the versionChian to set
	 */
	public void setVersionChian(String versionChian) {
		this.versionChian = versionChian;
	}



	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public Document(Users users, Department department, String summary,
			String context, Timestamp submitTime, String title, String keyword,
			int version, String userName, String departmentName) {
		this.users = users;
		this.department = department;
		this.summary = summary;
		this.context = context;
		this.submitTime = submitTime;
		this.title = title;
		this.keyword = keyword;
		this.version = version;
		this.userName = userName;
		this.departmentName = departmentName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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



	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}



	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
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

}