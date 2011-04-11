package com.combanc.itsm.pojo;

import java.util.Date;

public class SystemNotice {

	private Long id;

	private String authorName;

	private String title;

	private Date date;

	private String content;

	private String fileName;

	private String randomName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRandomName() {
		return randomName;
	}

	public void setRandomName(String randomName) {
		this.randomName = randomName;
	}
	
	public SystemNotice(){}

	public SystemNotice(long id, String authorName, String title, Date date,
			String content, String fileName, String randomName)
	{
		this.id = id;
		this.authorName = authorName;
		this.title = title;
		this.content = content;
		this.date = date;
		this.fileName = fileName;
		this.randomName = randomName;

	}

}
