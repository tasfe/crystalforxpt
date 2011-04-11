package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private String name;
	private String code;
	private String description;
	private Set userses = new HashSet(0);
	private Set serviceRequestsForSourceDept = new HashSet(0);
	private Set taskAllocations = new HashSet(0);
	private Set serviceRequestsForRequestDept = new HashSet(0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(Integer pid, String name, String code,
			String description, Set userses, Set serviceRequestsForSourceDept,
			Set taskAllocations, Set serviceRequestsForRequestDept) {
		this.pid = pid;
		this.name = name;
		this.code = code;
		this.description = description;
		this.userses = userses;
		this.serviceRequestsForSourceDept = serviceRequestsForSourceDept;
		this.taskAllocations = taskAllocations;
		this.serviceRequestsForRequestDept = serviceRequestsForRequestDept;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

	public Set getServiceRequestsForSourceDept() {
		return this.serviceRequestsForSourceDept;
	}

	public void setServiceRequestsForSourceDept(Set serviceRequestsForSourceDept) {
		this.serviceRequestsForSourceDept = serviceRequestsForSourceDept;
	}

	public Set getTaskAllocations() {
		return this.taskAllocations;
	}

	public void setTaskAllocations(Set taskAllocations) {
		this.taskAllocations = taskAllocations;
	}

	public Set getServiceRequestsForRequestDept() {
		return this.serviceRequestsForRequestDept;
	}

	public void setServiceRequestsForRequestDept(
			Set serviceRequestsForRequestDept) {
		this.serviceRequestsForRequestDept = serviceRequestsForRequestDept;
	}

}