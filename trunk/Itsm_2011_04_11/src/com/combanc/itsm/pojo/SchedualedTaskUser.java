package com.combanc.itsm.pojo;

import java.sql.Timestamp;

import com.combanc.itsm.pojo.SchedualedTaskDetail;
import com.combanc.itsm.pojo.Users;

public class SchedualedTaskUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private SchedualedTaskDetail schedualedTaskDetail;
	private Users users;
	private Timestamp finishTime;
	private String finishResult;
	private Integer flag;
	private Integer outOfDay;

	// Constructors

	/** default constructor */
	public SchedualedTaskUser() {
	}

	/** full constructor */
	public SchedualedTaskUser(SchedualedTaskDetail schedualedTaskDetail,
			Users users, Timestamp finishTime, String finishResult,Integer flag, Integer outOfDay) {
		this.schedualedTaskDetail = schedualedTaskDetail;
		this.users = users;
		this.finishTime = finishTime;
		this.finishResult = finishResult;
		this.flag=flag;
		this.outOfDay=outOfDay;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SchedualedTaskDetail getSchedualedTaskDetail() {
		return this.schedualedTaskDetail;
	}

	public void setSchedualedTaskDetail(
			SchedualedTaskDetail schedualedTaskDetail) {
		this.schedualedTaskDetail = schedualedTaskDetail;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Timestamp getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}

	public String getFinishResult() {
		return this.finishResult;
	}

	public void setFinishResult(String finishResult) {
		this.finishResult = finishResult;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getOutOfDay() {
		return outOfDay;
	}

	public void setOutOfDay(Integer outOfDay) {
		this.outOfDay = outOfDay;
	}

}