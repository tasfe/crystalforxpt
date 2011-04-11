package com.combanc.itsm.pojo;

/**
 * ConfigRecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ConfigRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private AssetsType assetsType;
	private String recordName;
	private String recordDescription;
	private Integer isAssetsinfo;

	// Constructors

	/** default constructor */
	public ConfigRecord() {
	}

	/** full constructor */
	public ConfigRecord(AssetsType assetsType, String recordName,
			String recordDescription, Integer isAssetsinfo) {
		this.assetsType = assetsType;
		this.recordName = recordName;
		this.recordDescription = recordDescription;
		this.isAssetsinfo = isAssetsinfo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AssetsType getAssetsType() {
		return this.assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	public String getRecordName() {
		return this.recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public String getRecordDescription() {
		return this.recordDescription;
	}

	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}

	public Integer getIsAssetsinfo() {
		return this.isAssetsinfo;
	}

	public void setIsAssetsinfo(Integer isAssetsinfo) {
		this.isAssetsinfo = isAssetsinfo;
	}

}