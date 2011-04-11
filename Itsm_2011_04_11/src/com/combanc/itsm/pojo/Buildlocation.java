package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Buildlocation entity. @author MyEclipse Persistence Tools
 */

public class Buildlocation implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer pid;
	private String code;
	private String allname;
	private Set assetsBases = new HashSet(0);

	// Constructors

	/** default constructor */
	public Buildlocation() {
	}

	/** full constructor */
	public Buildlocation(String name, Integer pid, String code, String allname,
			Set assetsBases) {
		this.name = name;
		this.pid = pid;
		this.code = code;
		this.allname = allname;
		this.assetsBases = assetsBases;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAllname() {
		return this.allname;
	}

	public void setAllname(String allname) {
		this.allname = allname;
	}

	public Set getAssetsBases() {
		return this.assetsBases;
	}

	public void setAssetsBases(Set assetsBases) {
		this.assetsBases = assetsBases;
	}

}