package com.combanc.itsm.pojo;

import java.util.Date;

/**
 * Schedule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Schedule implements java.io.Serializable {

	// Fields

	private Integer id;
	//private Integer executor;
	//private Integer assigner;
	private String content;
	private Date adate;
	private Date atime;
	private String hour;
	private String minute;
	private Integer level;
	private Integer status;
	private Users userByAssigner;
	private Users userByExecutor;
	
	private String title;
	private String detail;
	

	// Constructors
	
	

	public Schedule(Integer id, String content, Date adate, Date atime,
			String hour, String minute, Integer level, Integer status,
			Users userByAssigner, Users userByExecutor, String title,
			String detail) {
		super();
		this.id = id;
		this.content = content;
		this.adate = adate;
		this.atime = atime;
		this.hour = hour;
		this.minute = minute;
		this.level = level;
		this.status = status;
		this.userByAssigner = userByAssigner;
		this.userByExecutor = userByExecutor;
		this.title = title;
		this.detail = detail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


	/** default constructor */
	public Schedule() {
	}

	/** minimal constructor */
	public Schedule(Date adate, Date atime) {
		this.adate = adate;
		this.atime = atime;
	}

	/** full constructor */


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAdate() {
		return this.adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public Date getAtime() {
		return this.atime;
	}

	public void setAtime(Date atime) {
		this.atime = atime;
	}

	public String getHour() {
		return this.hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return this.minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


	public Users getUserByAssigner() {
		return userByAssigner;
	}

	public void setUserByAssigner(Users userByAssigner) {
		this.userByAssigner = userByAssigner;
	}

	public Users getUserByExecutor() {
		return userByExecutor;
	}

	public void setUserByExecutor(Users userByExecutor) {
		this.userByExecutor = userByExecutor;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}