package com.combanc.itsm.pojo;

import java.util.Date;

public class PersonalNotice {
	
	private Long id;
	private long userId;
	private String title;
	private String content;
	private String from;
	private String to;
	private String cc;
	private Date date;
	private boolean read;
	private int usertype;

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public PersonalNotice() {
	}

	public PersonalNotice(Long id, long userId, String title, String content,
			String from, String to, String cc, Date date, boolean read, int usertype) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.date = date;
		this.read = read;
		this.usertype = usertype;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

}
