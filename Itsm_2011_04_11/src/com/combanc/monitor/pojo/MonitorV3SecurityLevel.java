package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorV3SecurityLevel entity. @author MyEclipse Persistence Tools
 */

public class MonitorV3SecurityLevel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Byte level;
	private String name;
	private String note;
	private Set monitorV3Paramses = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorV3SecurityLevel() {
	}

	/** minimal constructor */
	public MonitorV3SecurityLevel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public MonitorV3SecurityLevel(Integer id, Byte level, String name,
			String note, Set monitorV3Paramses) {
		this.id = id;
		this.level = level;
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

	public Byte getLevel() {
		return this.level;
	}

	public void setLevel(Byte level) {
		this.level = level;
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