package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorReportTask entity. @author MyEclipse Persistence Tools
 */

public class MonitorReportTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String intro;
	private String url;
	private String configXml;
	private Timestamp addTime;
	private String prop1;
	private String prop2;
	private String prop3;
	private String prop4;
	private Integer status;

	// Constructors

	/** default constructor */
	public MonitorReportTask() {
	}

	/** full constructor */
	public MonitorReportTask(String name, String intro, String url,
			String configXml, Timestamp addTime, String prop1, String prop2,
			String prop3, String prop4, Integer status) {
		this.name = name;
		this.intro = intro;
		this.url = url;
		this.configXml = configXml;
		this.addTime = addTime;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.prop4 = prop4;
		this.status = status;
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

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getConfigXml() {
		return this.configXml;
	}

	public void setConfigXml(String configXml) {
		this.configXml = configXml;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getProp1() {
		return this.prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return this.prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public String getProp3() {
		return this.prop3;
	}

	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}

	public String getProp4() {
		return this.prop4;
	}

	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}