package com.combanc.monitor.pojo;

/**
 * MonitorSystemParam entity. @author MyEclipse Persistence Tools
 */

public class MonitorSystemParam implements java.io.Serializable {

	// Fields

	private String code;
	private String param;
	private String value;
	private String highValue;
	private String note;
	private String type;
	private Integer isuse;

	// Constructors

	/** default constructor */
	public MonitorSystemParam() {
	}
	
	/** default constructor */
	public MonitorSystemParam(String code) {
		this.code = code;
	}

	/** full constructor */
	public MonitorSystemParam(String param, String value, String highValue,String note,
			String type, Integer isuse) {
		this.param = param;
		this.value = value;
		this.highValue=highValue;
		this.note = note;
		this.type = type;
		this.isuse = isuse;
	}

	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIsuse() {
		return this.isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public String getHighValue() {
		return highValue;
	}

	public void setHighValue(String highValue) {
		this.highValue = highValue;
	}

}