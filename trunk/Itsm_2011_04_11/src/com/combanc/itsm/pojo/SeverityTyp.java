package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * SeverityTyp entity. @author MyEclipse Persistence Tools
 */

public class SeverityTyp implements java.io.Serializable {

	// Fields

	private Integer id;
	private String severityType;
	private Integer severityValue;
	private Integer category;
	private String type;

	// Constructors

	/** default constructor */
	public SeverityTyp() {
	}

	/** full constructor */
	public SeverityTyp(String severityType, Integer severityValue,
			Integer category, String type, Set serviceRequestsForEmergency,
			Set serviceRequestsForEssential) {
		this.severityType = severityType;
		this.severityValue = severityValue;
		this.category = category;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeverityType() {
		return this.severityType;
	}

	public void setSeverityType(String severityType) {
		this.severityType = severityType;
	}

	public Integer getSeverityValue() {
		return this.severityValue;
	}

	public void setSeverityValue(Integer severityValue) {
		this.severityValue = severityValue;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}