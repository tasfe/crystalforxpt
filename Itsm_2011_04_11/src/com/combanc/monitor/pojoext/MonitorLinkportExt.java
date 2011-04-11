package com.combanc.monitor.pojoext;

import com.combanc.monitor.pojo.MonitorSubnet;

/**
 * MonitorLinkport entity. @author MyEclipse Persistence Tools
 */

public class MonitorLinkportExt implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorSubnet monitorSubnet;
	private String ip;
	private String port;
	private String interface_;
	private String description;
	private String downlinkIp;
	private String downlinkPort;
	private String downlinkInterface;
	private String downlinkDesc;
	private Integer inode;
	private Integer edge;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	
	// 扩展列 LinkportDiscovery算法使用
	private String type;
	private String direction;
	private int seenNum;

	// Constructors

	/** default constructor */
	public MonitorLinkportExt() {
	}

	/** full constructor */
	public MonitorLinkportExt(MonitorSubnet monitorSubnet, String ip, String port,
			String interface_, String description, String downlinkIp,
			String downlinkPort, String downlinkInterface, String downlinkDesc,
			Integer inode, Integer edge, String note1, String note2,
			String note3, String note4) {
		this.monitorSubnet = monitorSubnet;
		this.ip = ip;
		this.port = port;
		this.interface_ = interface_;
		this.description = description;
		this.downlinkIp = downlinkIp;
		this.downlinkPort = downlinkPort;
		this.downlinkInterface = downlinkInterface;
		this.downlinkDesc = downlinkDesc;
		this.inode = inode;
		this.edge = edge;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorSubnet getMonitorSubnet() {
		return this.monitorSubnet;
	}

	public void setMonitorSubnet(MonitorSubnet monitorSubnet) {
		this.monitorSubnet = monitorSubnet;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getInterface_() {
		return this.interface_;
	}

	public void setInterface_(String interface_) {
		this.interface_ = interface_;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDownlinkIp() {
		return this.downlinkIp;
	}

	public void setDownlinkIp(String downlinkIp) {
		this.downlinkIp = downlinkIp;
	}

	public String getDownlinkPort() {
		return this.downlinkPort;
	}

	public void setDownlinkPort(String downlinkPort) {
		this.downlinkPort = downlinkPort;
	}

	public String getDownlinkInterface() {
		return this.downlinkInterface;
	}

	public void setDownlinkInterface(String downlinkInterface) {
		this.downlinkInterface = downlinkInterface;
	}

	public String getDownlinkDesc() {
		return this.downlinkDesc;
	}

	public void setDownlinkDesc(String downlinkDesc) {
		this.downlinkDesc = downlinkDesc;
	}

	public Integer getInode() {
		return this.inode;
	}

	public void setInode(Integer inode) {
		this.inode = inode;
	}

	public Integer getEdge() {
		return this.edge;
	}

	public void setEdge(Integer edge) {
		this.edge = edge;
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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	public void setSeenNum(int seenNum) {
		this.seenNum = seenNum;
	}

	public int getSeenNum() {
		return seenNum;
	}
}