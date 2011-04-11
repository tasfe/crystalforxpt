package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorMacInfo entity. @author MyEclipse Persistence Tools
 */

public class MonitorMacInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorBuildingZone monitorBuildingZone;
	private MonitorDevice monitorDevice;
	private MonitorBuilding monitorBuilding;
	private MonitorDepartment monitorDepartment;
	private String mac;
	private String ip;
	private String room;
	private Boolean movable;
	private String userName;
	private String phoneNum;
	private String email;
	private String userNum;
	private Timestamp modifyTime;
	private String note1;
	private String note2;

	// Constructors

	/** default constructor */
	public MonitorMacInfo() {
	}

	/** minimal constructor */
	public MonitorMacInfo(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public MonitorMacInfo(MonitorBuildingZone monitorBuildingZone,
			MonitorDevice monitorDevice, MonitorBuilding monitorBuilding,
			MonitorDepartment monitorDepartment, String mac, String ip,
			String room, Boolean movable, String userName, String phoneNum,
			String email, String userNum, Timestamp modifyTime, String note1,
			String note2) {
		this.monitorBuildingZone = monitorBuildingZone;
		this.monitorDevice = monitorDevice;
		this.monitorBuilding = monitorBuilding;
		this.monitorDepartment = monitorDepartment;
		this.mac = mac;
		this.ip = ip;
		this.room = room;
		this.movable = movable;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.userNum = userNum;
		this.modifyTime = modifyTime;
		this.note1 = note1;
		this.note2 = note2;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorBuildingZone getMonitorBuildingZone() {
		return this.monitorBuildingZone;
	}

	public void setMonitorBuildingZone(MonitorBuildingZone monitorBuildingZone) {
		this.monitorBuildingZone = monitorBuildingZone;
	}

	public MonitorDevice getMonitorDevice() {
		return this.monitorDevice;
	}

	public void setMonitorDevice(MonitorDevice monitorDevice) {
		this.monitorDevice = monitorDevice;
	}

	public MonitorBuilding getMonitorBuilding() {
		return this.monitorBuilding;
	}

	public void setMonitorBuilding(MonitorBuilding monitorBuilding) {
		this.monitorBuilding = monitorBuilding;
	}

	public MonitorDepartment getMonitorDepartment() {
		return this.monitorDepartment;
	}

	public void setMonitorDepartment(MonitorDepartment monitorDepartment) {
		this.monitorDepartment = monitorDepartment;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Boolean getMovable() {
		return this.movable;
	}

	public void setMovable(Boolean movable) {
		this.movable = movable;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

}