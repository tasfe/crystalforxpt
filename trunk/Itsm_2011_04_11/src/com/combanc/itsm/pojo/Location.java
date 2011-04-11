package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Location entity. @author MyEclipse Persistence Tools
 */

public class Location implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String content;
	private Integer pid;
	private String code;
	private String locationSc;
	private Set roleteams = new HashSet(0);
	private Set userses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Location() {
	}

	/** full constructor */
	public Location(String name, String content, Integer pid, String code,
			String locationSc, Set roleteams, Set userses) {
		this.name = name;
		this.content = content;
		this.pid = pid;
		this.code = code;
		this.locationSc = locationSc;
		this.roleteams = roleteams;
		this.userses = userses;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocationSc() {
		return this.locationSc;
	}

	public void setLocationSc(String locationSc) {
		this.locationSc = locationSc;
	}

	public Set getRoleteams() {
		return this.roleteams;
	}

	public void setRoleteams(Set roleteams) {
		this.roleteams = roleteams;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

}