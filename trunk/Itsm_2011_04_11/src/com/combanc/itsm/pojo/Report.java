package com.combanc.itsm.pojo;

import java.util.Date;

/**
 * Report entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Report implements java.io.Serializable {

	// Fields

	private Integer id;
	private String reportTitle;
	private String reportValue;
	private String creator;
	private Integer linkto;
	private Integer linkitem;
	private Date createtime;
	private String requdept;

	// Constructors

	/** default constructor */
	public Report() {
	}

	/** full constructor */
	public Report(String reportTitle, String reportValue, String creator,
			Integer linkto, Integer linkitem, Date createtime, String requdept) {
		this.reportTitle = reportTitle;
		this.reportValue = reportValue;
		this.creator = creator;
		this.linkto = linkto;
		this.linkitem = linkitem;
		this.createtime = createtime;
		this.requdept = requdept;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportTitle() {
		return this.reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getreportValue() {
		return this.reportValue;
	}

	public void setreportValue(String reportValue) {
		this.reportValue = reportValue;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getLinkto() {
		return this.linkto;
	}

	public void setLinkto(Integer linkto) {
		this.linkto = linkto;
	}

	public Integer getLinkitem() {
		return this.linkitem;
	}

	public void setLinkitem(Integer linkitem) {
		this.linkitem = linkitem;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getRequdept() {
		return this.requdept;
	}

	public void setRequdept(String requdept) {
		this.requdept = requdept;
	}

}