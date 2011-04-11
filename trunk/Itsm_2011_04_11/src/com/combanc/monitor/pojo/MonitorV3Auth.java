package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorV3Auth entity. @author MyEclipse Persistence Tools
 */

public class MonitorV3Auth implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer protocol;
	private String name;
	private String note;
	private Set monitorV3Paramses = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorV3Auth() {
	}

	/** minimal constructor */
	public MonitorV3Auth(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public MonitorV3Auth(Integer id, Integer protocol, String name,
			String note, Set monitorV3Paramses) {
		this.id = id;
		this.protocol = protocol;
		this.name = name;
		this.note = note;
		this.monitorV3Paramses = monitorV3Paramses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProtocol() {
		return this.protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getMonitorV3Paramses() {
		return this.monitorV3Paramses;
	}

	public void setMonitorV3Paramses(Set monitorV3Paramses) {
		this.monitorV3Paramses = monitorV3Paramses;
	}

}