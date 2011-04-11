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
 * AssetsProducer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assets_producer", catalog = "itsm")
public class AssetsProducer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tel;
	private Integer level;
	private String persion;
	private String persionTel;
	private String address;
	private String description;
	private Integer type;
	private String name;
	private Set<AssetsBase> assetsBasesForSupportId = new HashSet<AssetsBase>(0);
	private Set<AssetsBase> assetsBasesForProduceId = new HashSet<AssetsBase>(0);

	// Constructors

	/** default constructor */
	public AssetsProducer() {
	}

	/** full constructor */
	public AssetsProducer(String tel, Integer level, String persion,
			String persionTel, String address, String description,
			Integer type, String name, Set<AssetsBase> assetsBasesForSupportId,
			Set<AssetsBase> assetsBasesForProduceId) {
		this.tel = tel;
		this.level = level;
		this.persion = persion;
		this.persionTel = persionTel;
		this.address = address;
		this.description = description;
		this.type = type;
		this.name = name;
		this.assetsBasesForSupportId = assetsBasesForSupportId;
		this.assetsBasesForProduceId = assetsBasesForProduceId;
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

	@Column(name = "tel")
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "persion")
	public String getPersion() {
		return this.persion;
	}

	public void setPersion(String persion) {
		this.persion = persion;
	}

	@Column(name = "persion_tel")
	public String getPersionTel() {
		return this.persionTel;
	}

	public void setPersionTel(String persionTel) {
		this.persionTel = persionTel;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetsProducerBySupportId")
	public Set<AssetsBase> getAssetsBasesForSupportId() {
		return this.assetsBasesForSupportId;
	}

	public void setAssetsBasesForSupportId(
			Set<AssetsBase> assetsBasesForSupportId) {
		this.assetsBasesForSupportId = assetsBasesForSupportId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetsProducerByProduceId")
	public Set<AssetsBase> getAssetsBasesForProduceId() {
		return this.assetsBasesForProduceId;
	}

	public void setAssetsBasesForProduceId(
			Set<AssetsBase> assetsBasesForProduceId) {
		this.assetsBasesForProduceId = assetsBasesForProduceId;
	}

}