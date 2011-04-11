package com.combanc.monitor.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * MonitorV3Params entity. @author MyEclipse Persistence Tools
 */

public class MonitorV3Params implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorV3Auth monitorV3Auth;
	private MonitorV3Priv monitorV3Priv;
	private MonitorV3SecurityLevel monitorV3SecurityLevel;
	private String name;
	private Timestamp modifyTime;
	private String note;
	private String contextName;
	private String contextId;
	private String userName;
	private String authPassword;
	private String privPassword;
	private String engineId;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	private Set monitorDevices = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorV3Params() {
	}

	/** minimal constructor */
	public MonitorV3Params(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public MonitorV3Params(MonitorV3Auth monitorV3Auth,
			MonitorV3Priv monitorV3Priv,
			MonitorV3SecurityLevel monitorV3SecurityLevel, String name,
			Timestamp modifyTime, String note, String contextName,
			String contextId, String userName, String authPassword,
			String privPassword, String engineId, String note1, String note2,
			String note3, String note4, Set monitorDevices) {
		this.monitorV3Auth = monitorV3Auth;
		this.monitorV3Priv = monitorV3Priv;
		this.monitorV3SecurityLevel = monitorV3SecurityLevel;
		this.name = name;
		this.modifyTime = modifyTime;
		this.note = note;
		this.contextName = contextName;
		this.contextId = contextId;
		this.userName = userName;
		this.authPassword = authPassword;
		this.privPassword = privPassword;
		this.engineId = engineId;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.monitorDevices = monitorDevices;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorV3Auth getMonitorV3Auth() {
		return this.monitorV3Auth;
	}

	public void setMonitorV3Auth(MonitorV3Auth monitorV3Auth) {
		this.monitorV3Auth = monitorV3Auth;
	}

	public MonitorV3Priv getMonitorV3Priv() {
		return this.monitorV3Priv;
	}

	public void setMonitorV3Priv(MonitorV3Priv monitorV3Priv) {
		this.monitorV3Priv = monitorV3Priv;
	}

	public MonitorV3SecurityLevel getMonitorV3SecurityLevel() {
		return this.monitorV3SecurityLevel;
	}

	public void setMonitorV3SecurityLevel(
			MonitorV3SecurityLevel monitorV3SecurityLevel) {
		this.monitorV3SecurityLevel = monitorV3SecurityLevel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getContextName() {
		return this.contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public String getContextId() {
		return this.contextId;
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthPassword() {
		return this.authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public String getPrivPassword() {
		return this.privPassword;
	}

	public void setPrivPassword(String privPassword) {
		this.privPassword = privPassword;
	}

	public String getEngineId() {
		return this.engineId;
	}

	public void setEngineId(String engineId) {
		this.engineId = engineId;
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

	public String getNote3() {
		return this.note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return this.note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public Set getMonitorDevices() {
		return this.monitorDevices;
	}

	public void setMonitorDevices(Set monitorDevices) {
		this.monitorDevices = monitorDevices;
	}

}