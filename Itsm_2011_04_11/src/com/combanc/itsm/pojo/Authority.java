package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Authority entity. @author MyEclipse Persistence Tools
 */

public class Authority implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer pid;
	private Set roleteams = new HashSet(0);

	// Constructors

	/** default constructor */
	public Authority() {
	}

	/** full constructor */
	public Authority(String name, Integer pid, Set roleteams) {
		this.name = name;
		this.pid = pid;
		this.roleteams = roleteams;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Set getRoleteams() {
		return this.roleteams;
	}

	public void setRoleteams(Set roleteams) {
		this.roleteams = roleteams;
	}

}