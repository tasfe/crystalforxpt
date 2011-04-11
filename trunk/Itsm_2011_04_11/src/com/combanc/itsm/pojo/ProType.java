package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * ProType entity. @author MyEclipse Persistence Tools
 */

public class ProType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typeName;
	private Set proDefinitions = new HashSet(0);

	// Constructors

	/** default constructor */
	public ProType() {
	}

	/** full constructor */
	public ProType(String typeName, Set proDefinitions) {
		this.typeName = typeName;
		this.proDefinitions = proDefinitions;
	}

	// Property accessors

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Set getProDefinitions() {
		return this.proDefinitions;
	}

	public void setProDefinitions(Set proDefinitions) {
		this.proDefinitions = proDefinitions;
	}

}