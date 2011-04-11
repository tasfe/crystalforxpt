package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * AssetsHistoryState entity. @author MyEclipse Persistence Tools
 */

public class AssetsHistoryState implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private Integer state;
	private Timestamp applyDate;
	private Integer persionId;
	private Timestamp backDate;

	// Constructors

	/** default constructor */
	public AssetsHistoryState() {
	}

	/** minimal constructor */
	public AssetsHistoryState(Integer state) {
		this.state = state;
	}

	/** full constructor */
	public AssetsHistoryState(String code, Integer state, Timestamp applyDate,
			Integer persionId, Timestamp backDate) {
		this.code = code;
		this.state = state;
		this.applyDate = applyDate;
		this.persionId = persionId;
		this.backDate = backDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}

	public Integer getPersionId() {
		return this.persionId;
	}

	public void setPersionId(Integer persionId) {
		this.persionId = persionId;
	}

	public Timestamp getBackDate() {
		return this.backDate;
	}

	public void setBackDate(Timestamp backDate) {
		this.backDate = backDate;
	}

}