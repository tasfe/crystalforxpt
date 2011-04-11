package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * AssetsState entity. @author MyEclipse Persistence Tools
 */

public class AssetsState implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer sequence;
	private String remark1;
	private Set assetsBases = new HashSet(0);
	private Set assetsChanges = new HashSet(0);

	// Constructors

	/** default constructor */
	public AssetsState() {
	}

	/** full constructor */
	public AssetsState(String name, Integer sequence, String remark1,
			Set assetsBases, Set assetsChanges) {
		this.name = name;
		this.sequence = sequence;
		this.remark1 = remark1;
		this.assetsBases = assetsBases;
		this.assetsChanges = assetsChanges;
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

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public Set getAssetsBases() {
		return this.assetsBases;
	}

	public void setAssetsBases(Set assetsBases) {
		this.assetsBases = assetsBases;
	}

	public Set getAssetsChanges() {
		return this.assetsChanges;
	}

	public void setAssetsChanges(Set assetsChanges) {
		this.assetsChanges = assetsChanges;
	}

}