package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * AssetsType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assets_type", catalog = "itsm")
public class AssetsType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String flag;
	private String description;
	private Integer pid;
	private String code;
	private String countryname;
	private String countryCode;
	private Set<AssetsBase> assetsBases = new HashSet<AssetsBase>(0);
	private Set<ConfigRecord> configRecords = new HashSet<ConfigRecord>(0);
	private Set<AssetsConfig> assetsConfigs = new HashSet<AssetsConfig>(0);

	// Constructors

	/** default constructor */
	public AssetsType() {
	}

	/** full constructor */
	public AssetsType(String name, String flag, String description,
			Integer pid, Set<AssetsBase> assetsBases,
			Set<ConfigRecord> configRecords, Set<AssetsConfig> assetsConfigs) {
		this.name = name;
		this.flag = flag;
		this.description = description;
		this.pid = pid;
		this.code = code;
		this.countryname = countryname;
		this.countryCode = countryCode;
		this.assetsBases = assetsBases;
		this.configRecords = configRecords;
		this.assetsConfigs = assetsConfigs;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "flag")
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "pid")
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

	public String getCountryname() {
		return this.countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetsType")
	public Set<ConfigRecord> getConfigRecords() {
		return this.configRecords;
	}

	public void setConfigRecords(Set<ConfigRecord> configRecords) {
		this.configRecords = configRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetsType")
	public Set<AssetsBase> getAssetsBases() {
		return this.assetsBases;
	}

	public void setAssetsBases(Set<AssetsBase> assetsBases) {
		this.assetsBases = assetsBases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetsType")
	public Set<AssetsConfig> getAssetsConfigs() {
		return this.assetsConfigs;
	}

	public void setAssetsConfigs(Set<AssetsConfig> assetsConfigs) {
		this.assetsConfigs = assetsConfigs;
	}

}