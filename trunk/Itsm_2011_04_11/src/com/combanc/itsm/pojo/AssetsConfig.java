package com.combanc.itsm.pojo;

/**
 * AssetsConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssetsConfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private AssetsType assetsType;
	private Integer flag;
	private String configName;
	private String configColumnName;
	private String configStats;
	private Integer ischoose;

	// Constructors

	/** default constructor */
	public AssetsConfig() {
	}

	/** full constructor */
	public AssetsConfig(AssetsType assetsType, Integer flag, String configName,
			String configColumnName, String configStats, Integer ischoose) {
		this.assetsType = assetsType;
		this.flag = flag;
		this.configName = configName;
		this.configColumnName = configColumnName;
		this.configStats = configStats;
		this.ischoose = ischoose;
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

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getConfigName() {
		return this.configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigColumnName() {
		return this.configColumnName;
	}

	public void setConfigColumnName(String configColumnName) {
		this.configColumnName = configColumnName;
	}

	public String getConfigStats() {
		return this.configStats;
	}

	public void setConfigStats(String configStats) {
		this.configStats = configStats;
	}

	public Integer getIschoose() {
		return this.ischoose;
	}

	public void setIschoose(Integer ischoose) {
		this.ischoose = ischoose;
	}

}