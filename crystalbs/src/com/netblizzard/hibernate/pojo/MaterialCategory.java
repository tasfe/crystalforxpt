package com.netblizzard.hibernate.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MaterialCategory entity. @author MyEclipse Persistence Tools
 */

public class MaterialCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String note;
	private Integer isUse;
	private Set materials = new HashSet(0);

	// Constructors

	/** default constructor */
	public MaterialCategory() {
	}

	/** full constructor */
	public MaterialCategory(String name, String note, Integer isUse,
			Set materials) {
		this.name = name;
		this.note = note;
		this.isUse = isUse;
		this.materials = materials;
	}

	public String toString() {
		return this.name;
	}
	// Property accessors

	public MaterialCategory(int i) {
		this.id = i;
	}

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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getIsUse() {
		return this.isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Set getMaterials() {
		return this.materials;
	}

	public void setMaterials(Set materials) {
		this.materials = materials;
	}

}