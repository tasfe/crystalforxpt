package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * AssetsChange entity. @author MyEclipse Persistence Tools
 */

public class AssetsChange implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users usersByChargeid;
	private AssetsState assetsState;
	private Users usersByReceiverid;
	private Users usersByManagersid;
	private Users usersByUserid;
	private AssetsBase assetsBase;
	private Timestamp changeTime;
	private Integer equipments;
	private String changeDescription;
	private Long price;
	private String remark;

	// Constructors

	/** default constructor */
	public AssetsChange() {
	}

	/** minimal constructor */
	public AssetsChange(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

	/** full constructor */
	public AssetsChange(Users usersByChargeid, AssetsState assetsState,
			Users usersByReceiverid, Users usersByManagersid,
			Users usersByUserid, AssetsBase assetsBase, Timestamp changeTime,
			Integer equipments, String changeDescription, Long price,
			String remark) {
		this.usersByChargeid = usersByChargeid;
		this.assetsState = assetsState;
		this.usersByReceiverid = usersByReceiverid;
		this.usersByManagersid = usersByManagersid;
		this.usersByUserid = usersByUserid;
		this.assetsBase = assetsBase;
		this.changeTime = changeTime;
		this.equipments = equipments;
		this.changeDescription = changeDescription;
		this.price = price;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsersByChargeid() {
		return this.usersByChargeid;
	}

	public void setUsersByChargeid(Users usersByChargeid) {
		this.usersByChargeid = usersByChargeid;
	}

	public AssetsState getAssetsState() {
		return this.assetsState;
	}

	public void setAssetsState(AssetsState assetsState) {
		this.assetsState = assetsState;
	}

	public Users getUsersByReceiverid() {
		return this.usersByReceiverid;
	}

	public void setUsersByReceiverid(Users usersByReceiverid) {
		this.usersByReceiverid = usersByReceiverid;
	}

	public Users getUsersByManagersid() {
		return this.usersByManagersid;
	}

	public void setUsersByManagersid(Users usersByManagersid) {
		this.usersByManagersid = usersByManagersid;
	}

	public Users getUsersByUserid() {
		return this.usersByUserid;
	}

	public void setUsersByUserid(Users usersByUserid) {
		this.usersByUserid = usersByUserid;
	}

	public AssetsBase getAssetsBase() {
		return this.assetsBase;
	}

	public void setAssetsBase(AssetsBase assetsBase) {
		this.assetsBase = assetsBase;
	}

	public Timestamp getChangeTime() {
		return this.changeTime;
	}

	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}

	public Integer getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Integer equipments) {
		this.equipments = equipments;
	}

	public String getChangeDescription() {
		return this.changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}