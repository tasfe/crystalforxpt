package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorRealtimeCpu entity. @author MyEclipse Persistence Tools
 */

public class MonitorRealtimeCpu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Float data;
	private String ip;
	private String name;
	private String type;
	private Timestamp gatherTime;

	// Constructors

	/** default constructor */
	public MonitorRealtimeCpu() {
	}

	/** full constructor */
	public MonitorRealtimeCpu(Float data, String ip, String name, String type,
			Timestamp gatherTime) {
		this.data = data;
		this.ip = ip;
		this.name = name;
		this.type = type;
		this.gatherTime = gatherTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getData() {
		return this.data;
	}

	public void setData(Float data) {
		this.data = data;
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

	public Timestamp getGatherTime() {
		return this.gatherTime;
	}

	public void setGatherTime(Timestamp gatherTime) {
		this.gatherTime = gatherTime;
	}

}