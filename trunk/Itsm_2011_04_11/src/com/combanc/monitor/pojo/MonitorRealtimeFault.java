package com.combanc.monitor.pojo;

/**
 * MonitorRealtimeFault entity. @author MyEclipse Persistence Tools
 */

public class MonitorRealtimeFault implements java.io.Serializable {

	// Fields

	private Integer id;
	private String data;
	private String ip;
	private String name;
	private String type;

	// Constructors

	/** default constructor */
	public MonitorRealtimeFault() {
	}

	/** full constructor */
	public MonitorRealtimeFault(String data, String ip, String name,
			String type) {
		this.data = data;
		this.ip = ip;
		this.name = name;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
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

}