package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * ProDefinition entity. @author MyEclipse Persistence Tools
 */

public class ProDefinition implements java.io.Serializable {

	// Fields

	private Integer defId;
	private ProType proType;
	private String name;
	private String description;
	private Timestamp createtime;
	private String deployId;
	private String defXml;
	private String drawDefXml;
	private String isDefault;

	// Constructors

	/** default constructor */
	public ProDefinition() {
	}

	/** minimal constructor */
	public ProDefinition(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** full constructor */
	public ProDefinition(ProType proType, String name, String description,
			Timestamp createtime, String deployId, String defXml,
			String drawDefXml, String isDefault) {
		this.proType = proType;
		this.name = name;
		this.description = description;
		this.createtime = createtime;
		this.deployId = deployId;
		this.defXml = defXml;
		this.drawDefXml = drawDefXml;
		this.isDefault = isDefault;
	}

	// Property accessors

	public Integer getDefId() {
		return this.defId;
	}

	public void setDefId(Integer defId) {
		this.defId = defId;
	}

	public ProType getProType() {
		return this.proType;
	}

	public void setProType(ProType proType) {
		this.proType = proType;
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

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getDeployId() {
		return this.deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	public String getDefXml() {
		return this.defXml;
	}

	public void setDefXml(String defXml) {
		this.defXml = defXml;
	}

	public String getDrawDefXml() {
		return this.drawDefXml;
	}

	public void setDrawDefXml(String drawDefXml) {
		this.drawDefXml = drawDefXml;
	}

	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}