package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorSwitchModel entity. @author MyEclipse Persistence Tools
 */

public class MonitorSwitchModel implements java.io.Serializable {

	// Fields

	private Integer code;
	private String type;
	private String note;
	private String format;
	private Boolean modifiable;
	private String login;
	private String backup;
	private String ipMac;
	private String arpDisband;
	private String interfaceOpen;
	private String interfaceShutdown;
	private String snmpConfig;
	private String iosBackup;
	private String other;
	private String save;
	private String logout;
	private Integer isuse;
	private Set monitorDevices = new HashSet(0);

	// Constructors

	/** default constructor */
	public MonitorSwitchModel() {
	}

	/** full constructor */
	public MonitorSwitchModel(String type, String note, String format,
			Boolean modifiable, String login, String backup, String ipMac,
			String arpDisband, String interfaceOpen, String interfaceShutdown,
			String snmpConfig, String iosBackup, String other, String save,
			String logout, Integer isuse, Set monitorDevices) {
		this.type = type;
		this.note = note;
		this.format = format;
		this.modifiable = modifiable;
		this.login = login;
		this.backup = backup;
		this.ipMac = ipMac;
		this.arpDisband = arpDisband;
		this.interfaceOpen = interfaceOpen;
		this.interfaceShutdown = interfaceShutdown;
		this.snmpConfig = snmpConfig;
		this.iosBackup = iosBackup;
		this.other = other;
		this.save = save;
		this.logout = logout;
		this.isuse = isuse;
		this.monitorDevices = monitorDevices;
	}

	// Property accessors

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Boolean getModifiable() {
		return this.modifiable;
	}

	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getBackup() {
		return this.backup;
	}

	public void setBackup(String backup) {
		this.backup = backup;
	}

	public String getIpMac() {
		return this.ipMac;
	}

	public void setIpMac(String ipMac) {
		this.ipMac = ipMac;
	}

	public String getArpDisband() {
		return this.arpDisband;
	}

	public void setArpDisband(String arpDisband) {
		this.arpDisband = arpDisband;
	}

	public String getInterfaceOpen() {
		return this.interfaceOpen;
	}

	public void setInterfaceOpen(String interfaceOpen) {
		this.interfaceOpen = interfaceOpen;
	}

	public String getInterfaceShutdown() {
		return this.interfaceShutdown;
	}

	public void setInterfaceShutdown(String interfaceShutdown) {
		this.interfaceShutdown = interfaceShutdown;
	}

	public String getSnmpConfig() {
		return this.snmpConfig;
	}

	public void setSnmpConfig(String snmpConfig) {
		this.snmpConfig = snmpConfig;
	}

	public String getIosBackup() {
		return this.iosBackup;
	}

	public void setIosBackup(String iosBackup) {
		this.iosBackup = iosBackup;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getSave() {
		return this.save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public String getLogout() {
		return this.logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

	public Integer getIsuse() {
		return this.isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public Set getMonitorDevices() {
		return this.monitorDevices;
	}

	public void setMonitorDevices(Set monitorDevices) {
		this.monitorDevices = monitorDevices;
	}

}