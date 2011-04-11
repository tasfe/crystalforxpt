package com.combanc.monitor.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * MonitorUrlResponse entity. @author MyEclipse Persistence Tools
 */

public class MonitorUrlResponse implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String address;
	private String port;
	private String domain;
	private String username;
	private String password;
	private String realm;
	private String contain;
	private String nocontain;
	private String serverIp;
	private Integer replyTime;
	private Integer scan;
	private Timestamp addTime;
	private Timestamp flushTime;
	private Integer stateCode;
	private Integer type;
	private Integer subnet;
	private Set monitorUrlServices = new HashSet(0);
	private MonitorAlertPolicy alertPolicy;

	// Constructors

	/** default constructor */
	public MonitorUrlResponse() {
	}

	/** minimal constructor */
	public MonitorUrlResponse(Timestamp addTime) {
		this.addTime = addTime;
	}

	/** full constructor */
	public MonitorUrlResponse(String title, String address, String port,
			String domain, String username, String password, String realm,
			String contain, String nocontain, String serverIp,
			Integer replyTime, Integer scan, Timestamp addTime,Timestamp flushTime,
			Integer stateCode, Integer type, Integer subnet,
			Set monitorUrlServices) {
		this.title = title;
		this.address = address;
		this.port = port;
		this.domain = domain;
		this.username = username;
		this.password = password;
		this.realm = realm;
		this.contain = contain;
		this.nocontain = nocontain;
		this.serverIp = serverIp;
		this.replyTime = replyTime;
		this.scan = scan;
		this.addTime = addTime;
		this.flushTime = flushTime;
		this.stateCode = stateCode;
		this.type = type;
		this.subnet = subnet;
		this.monitorUrlServices = monitorUrlServices;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealm() {
		return this.realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getContain() {
		return this.contain;
	}

	public void setContain(String contain) {
		this.contain = contain;
	}

	public String getNocontain() {
		return this.nocontain;
	}

	public void setNocontain(String nocontain) {
		this.nocontain = nocontain;
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Integer getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Integer replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getScan() {
		return this.scan;
	}

	public void setScan(Integer scan) {
		this.scan = scan;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Integer getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSubnet() {
		return this.subnet;
	}

	public void setSubnet(Integer subnet) {
		this.subnet = subnet;
	}

	public Set getMonitorUrlServices() {
		return this.monitorUrlServices;
	}

	public void setMonitorUrlServices(Set monitorUrlServices) {
		this.monitorUrlServices = monitorUrlServices;
	}

	public MonitorAlertPolicy getAlertPolicy() {
		return alertPolicy;
	}

	public void setAlertPolicy(MonitorAlertPolicy alertPolicy) {
		this.alertPolicy = alertPolicy;
	}

	public Timestamp getFlushTime() {
		return flushTime;
	}

	public void setFlushTime(Timestamp flushTime) {
		this.flushTime = flushTime;
	}
	

}