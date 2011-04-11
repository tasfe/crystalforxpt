package com.combanc.itsm.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields
	private Integer id;
	private Department department;
	private Location location;
	private Integer managerId;
	private String username;
	private String password;
	private String truename;
	private String usertype;
	private String phone;
	private String cellphone;
	private String address;
	private String email;
	private Timestamp lastAccessTime;
	private String lastAccessIp;
	private Integer status;
	private Integer online;
	private String description;
	private String sex;
	private Timestamp birthday;
	private Integer add1;
	private String add2;
	private String add3;
	private Set roleteams = new HashSet(0);
	private Set serviceTransForServiceFrom = new HashSet(0);
	private Set serviceRequestsForOperatorId = new HashSet(0);
	private Set serviceRequestsForEngineerId = new HashSet(0);
	private Set serviceRequestsForOriginatorId = new HashSet(0);
	private Set serviceTransForServiceTo = new HashSet(0);
	private Set serviceRequestsForRequestUser = new HashSet(0);
	private Set knowledgeBase = new HashSet(0);  //关联知识库管理
	private Set schedulesForExecutor = new HashSet(0);
	private Set schedulesForAssigner = new HashSet(0);
	
	private Integer pending;       //手头待处理任务
	private Integer processing;    //手头处理中的任务

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(Department department, Location location, Integer managerId,
			String username, String password, String truename, String usertype,
			String phone, String cellphone, String address, String email,
			Timestamp lastAccessTime, String lastAccessIp, Integer status,
			Integer online, String description, String sex, Timestamp birthday,
			Integer add1, String add2, String add3, Set roleteams,
			Set serviceTransForServiceFrom, Set serviceRequestsForOperatorId,
			Set serviceRequestsForEngineerId, Set serviceTransForServiceTo,
			Set serviceRequestsForRequestUser) {
		this.department = department;
		this.location = location;
		this.managerId = managerId;
		this.username = username;
		this.password = password;
		this.truename = truename;
		this.usertype = usertype;
		this.phone = phone;
		this.cellphone = cellphone;
		this.address = address;
		this.email = email;
		this.lastAccessTime = lastAccessTime;
		this.lastAccessIp = lastAccessIp;
		this.status = status;
		this.online = online;
		this.description = description;
		this.sex = sex;
		this.birthday = birthday;
		this.add1 = add1;
		this.add2 = add2;
		this.add3 = add3;
		this.roleteams = roleteams;
		this.serviceTransForServiceFrom = serviceTransForServiceFrom;
		this.serviceRequestsForOperatorId = serviceRequestsForOperatorId;
		this.serviceRequestsForEngineerId = serviceRequestsForEngineerId;
		this.serviceTransForServiceTo = serviceTransForServiceTo;
		this.serviceRequestsForRequestUser = serviceRequestsForRequestUser;
	}

	// Property accessors



	public Department getDepartment() {
		return this.department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTruename() {
		return this.truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getLastAccessTime() {
		return this.lastAccessTime;
	}

	public void setLastAccessTime(Timestamp lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public String getLastAccessIp() {
		return this.lastAccessIp;
	}

	public void setLastAccessIp(String lastAccessIp) {
		this.lastAccessIp = lastAccessIp;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOnline() {
		return this.online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Integer getAdd1() {
		return this.add1;
	}

	public void setAdd1(Integer add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return this.add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getAdd3() {
		return this.add3;
	}

	public void setAdd3(String add3) {
		this.add3 = add3;
	}

	public Set getRoleteams() {
		return this.roleteams;
	}

	public void setRoleteams(Set roleteams) {
		this.roleteams = roleteams;
	}

	public Set getServiceTransForServiceFrom() {
		return this.serviceTransForServiceFrom;
	}

	public void setServiceTransForServiceFrom(Set serviceTransForServiceFrom) {
		this.serviceTransForServiceFrom = serviceTransForServiceFrom;
	}

	public Set getServiceRequestsForOperatorId() {
		return this.serviceRequestsForOperatorId;
	}

	public void setServiceRequestsForOperatorId(Set serviceRequestsForOperatorId) {
		this.serviceRequestsForOperatorId = serviceRequestsForOperatorId;
	}

	public Set getServiceRequestsForEngineerId() {
		return this.serviceRequestsForEngineerId;
	}

	public void setServiceRequestsForEngineerId(Set serviceRequestsForEngineerId) {
		this.serviceRequestsForEngineerId = serviceRequestsForEngineerId;
	}

	public Set getServiceTransForServiceTo() {
		return this.serviceTransForServiceTo;
	}

	public void setServiceTransForServiceTo(Set serviceTransForServiceTo) {
		this.serviceTransForServiceTo = serviceTransForServiceTo;
	}

	public Set getServiceRequestsForRequestUser() {
		return this.serviceRequestsForRequestUser;
	}

	public void setServiceRequestsForRequestUser(
			Set serviceRequestsForRequestUser) {
		this.serviceRequestsForRequestUser = serviceRequestsForRequestUser;
	}

	public Integer getPending() {
		return pending;
	}

	public void setPending(Integer pending) {
		this.pending = pending;
	}

	public Integer getProcessing() {
		return processing;
	}

	public void setProcessing(Integer processing) {
		this.processing = processing;
	}

	public Set getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(Set knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	public Set getServiceRequestsForOriginatorId() {
		return serviceRequestsForOriginatorId;
	}

	public void setServiceRequestsForOriginatorId(Set serviceRequestsForOriginatorId) {
		this.serviceRequestsForOriginatorId = serviceRequestsForOriginatorId;
	}

	public Set getSchedulesForExecutor() {
		return schedulesForExecutor;
	}

	public void setSchedulesForExecutor(Set schedulesForExecutor) {
		this.schedulesForExecutor = schedulesForExecutor;
	}

	public Set getSchedulesForAssigner() {
		return schedulesForAssigner;
	}

	public void setSchedulesForAssigner(Set schedulesForAssigner) {
		this.schedulesForAssigner = schedulesForAssigner;
	}

}