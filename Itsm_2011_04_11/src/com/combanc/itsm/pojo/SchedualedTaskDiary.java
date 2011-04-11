package com.combanc.itsm.pojo;

import java.sql.Timestamp;
import java.util.List;

/**
 * SchedualedTaskDiary entity. @author MyEclipse Persistence Tools
 */

public class SchedualedTaskDiary implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private String title;
	private String content;
	private Timestamp submitTime;
	private Integer progress;
	private Integer taskDetailId;
	private List accessoryList;

	// Constructors

	/** default constructor */
	public SchedualedTaskDiary() {
	}

	/** full constructor */
	public SchedualedTaskDiary(Users users, String title, String content,
			Timestamp submitTime, Integer progress, Integer taskDetailId) {
		this.users = users;
		this.title = title;
		this.content = content;
		this.submitTime = submitTime;
		this.progress = progress;
		this.taskDetailId = taskDetailId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}

	public Integer getProgress() {
		return this.progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public Integer getTaskDetailId() {
		return this.taskDetailId;
	}

	public void setTaskDetailId(Integer taskDetailId) {
		this.taskDetailId = taskDetailId;
	}

	public List getAccessoryList() {
		return accessoryList;
	}

	public void setAccessoryList(List accessoryList) {
		this.accessoryList = accessoryList;
	}

}