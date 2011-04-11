package com.combanc.monitor.pojo;

/**
 * MonitorPingDestType entity. @author MyEclipse Persistence Tools
 */

public class MonitorPingDestType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String iconConn;
	private String iconDisconn;
	private String iconForbid;
	private String note;

	// Constructors

	/** default constructor */
	public MonitorPingDestType() {
	}

	/** minimal constructor */
	public MonitorPingDestType(String iconConn, String iconDisconn,
			String iconForbid) {
		this.iconConn = iconConn;
		this.iconDisconn = iconDisconn;
		this.iconForbid = iconForbid;
	}

	/** full constructor */
	public MonitorPingDestType(String name, String iconConn,
			String iconDisconn, String iconForbid, String note) {
		this.name = name;
		this.iconConn = iconConn;
		this.iconDisconn = iconDisconn;
		this.iconForbid = iconForbid;
		this.note = note;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconConn() {
		return this.iconConn;
	}

	public void setIconConn(String iconConn) {
		this.iconConn = iconConn;
	}

	public String getIconDisconn() {
		return this.iconDisconn;
	}

	public void setIconDisconn(String iconDisconn) {
		this.iconDisconn = iconDisconn;
	}

	public String getIconForbid() {
		return this.iconForbid;
	}

	public void setIconForbid(String iconForbid) {
		this.iconForbid = iconForbid;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}