package com.combanc.monitor.pojo;

/**
 * MonitorRealtimeUse entity. @author MyEclipse Persistence Tools
 */

public class MonitorRealtimeUse implements java.io.Serializable {

	// Fields

	private Integer id;
	private Float totalNum;
	private Float effectiveNum;
	private String ip;
	private String name;
	private String type;
	private Boolean exist;
	private Float rate;

	// Constructors

	/** default constructor */
	public MonitorRealtimeUse() {
	}

	/** full constructor */
	public MonitorRealtimeUse(Float totalNum, Float effectiveNum, String ip,
			String name, String type, Boolean exist, Float rate) {
		this.totalNum = totalNum;
		this.effectiveNum = effectiveNum;
		this.ip = ip;
		this.name = name;
		this.type = type;
		this.exist = exist;
		this.rate = rate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Float totalNum) {
		this.totalNum = totalNum;
	}

	public Float getEffectiveNum() {
		return this.effectiveNum;
	}

	public void setEffectiveNum(Float effectiveNum) {
		this.effectiveNum = effectiveNum;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getExist() {
		return this.exist;
	}

	public void setExist(Boolean exist) {
		this.exist = exist;
	}

	public Float getRate() {
		return this.rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

}