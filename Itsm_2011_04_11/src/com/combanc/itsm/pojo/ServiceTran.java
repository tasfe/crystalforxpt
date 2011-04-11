package com.combanc.itsm.pojo;

import java.sql.Timestamp;

/**
 * ServiceTran entity. @author MyEclipse Persistence Tools
 */

public class ServiceTran implements java.io.Serializable {

	// Fields

	private Integer id;
	private ServiceCategory serviceCategory;
	private Users usersByServiceFrom;
	private Users usersByServiceTo;
	private String requNo;
	private String autoOrEndTime;
	private String serviceTilte;
	private String note;
	private String type;
	private Timestamp operatorTime;
	private Integer minutes;
	private String otherNote;
	private String users;

	// Constructors

	/** default constructor */
	public ServiceTran() {
	}

	/** full constructor */
	public ServiceTran(ServiceCategory serviceCategory,
			Users usersByServiceFrom, Users usersByServiceTo, String requNo,
			String autoOrEndTime, String serviceTilte, String note,
			String type, Timestamp operatorTime, Integer minutes,
			String otherNote, String users) {
		this.serviceCategory = serviceCategory;
		this.usersByServiceFrom = usersByServiceFrom;
		this.usersByServiceTo = usersByServiceTo;
		this.requNo = requNo;
		this.autoOrEndTime = autoOrEndTime;
		this.serviceTilte = serviceTilte;
		this.note = note;
		this.type = type;
		this.operatorTime = operatorTime;
		this.minutes = minutes;
		this.otherNote = otherNote;
		this.users = users;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceCategory getServiceCategory() {
		return this.serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public Users getUsersByServiceFrom() {
		return this.usersByServiceFrom;
	}

	public void setUsersByServiceFrom(Users usersByServiceFrom) {
		this.usersByServiceFrom = usersByServiceFrom;
	}

	public Users getUsersByServiceTo() {
		return this.usersByServiceTo;
	}

	public void setUsersByServiceTo(Users usersByServiceTo) {
		this.usersByServiceTo = usersByServiceTo;
	}

	public String getRequNo() {
		return this.requNo;
	}

	public void setRequNo(String requNo) {
		this.requNo = requNo;
	}

	public String getAutoOrEndTime() {
		return this.autoOrEndTime;
	}

	public void setAutoOrEndTime(String autoOrEndTime) {
		this.autoOrEndTime = autoOrEndTime;
	}

	public String getServiceTilte() {
		return this.serviceTilte;
	}

	public void setServiceTilte(String serviceTilte) {
		this.serviceTilte = serviceTilte;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getOperatorTime() {
		return this.operatorTime;
	}

	public void setOperatorTime(Timestamp operatorTime) {
		this.operatorTime = operatorTime;
	}

	public Integer getMinutes() {
		return this.minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public String getOtherNote() {
		return this.otherNote;
	}

	public void setOtherNote(String otherNote) {
		this.otherNote = otherNote;
	}

	public String getUsers() {
		return this.users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

}