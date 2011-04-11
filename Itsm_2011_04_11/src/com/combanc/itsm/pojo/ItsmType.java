package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * ItsmType entity. @author MyEclipse Persistence Tools
 */

public class ItsmType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Integer pid;
	private String code;
	private Set assetsBases = new HashSet(0);

	// Constructors

	/** default constructor */
	public ItsmType() {
	}

	/** full constructor */
	public ItsmType(String name, String description, Integer pid, String code,
			Set assetsBases) {
		this.name = name;
		this.description = description;
		this.pid = pid;
		this.code = code;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set getAssetsBases() {
		return this.assetsBases;
	}

	public void setAssetsBases(Set assetsBases) {
		this.assetsBases = assetsBases;
	}

}