package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MonitorDevice entity. @author MyEclipse Persistence Tools
 */

public class MonitorDevice implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mac;
	private String cpuOid;
	private MonitorBuilding monitorBuilding;
	private MonitorAlertPolicy monitorAlertPolicy;
	private MonitorSwitchModel monitorSwitchModel;
	private MonitorDeviceType monitorDeviceType;
	private MonitorVendorMac monitorVendorMac;
	private MonitorBuildingZone monitorBuildingZone;
	private String ip;
	private String readCommunity;
	private String writeCommunity;
	private String description;
	private String name;
	private String nameCn;
	private String customTitle;
	private String passwordLogin;
	private String passwordEnable;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	private Integer archiveUserNum = 0;
	private Integer snapUserNum = 0;
	private MonitorV3Params monitorV3Params;
	private Set monitorComputers = new HashSet(0);
	private Set monitorSubnetDevices = new HashSet(0);
	private Set monitorUrlServices = new HashSet(0);
	private Set monitorVlans = new HashSet(0);
	private Set monitorMacInfos = new HashSet(0);
	private MonitorInterfaceCache monitorInterfaceCache;
	private Set monitorServerProcesses = new HashSet(0);
	// private Set monitorSnapshots = new HashSet(0);
	private MonitorTopologyNode monitorTopologyNode;

	// Constructors

	public MonitorTopologyNode getMonitorTopologyNode() {
		return monitorTopologyNode;
	}

	public void setMonitorTopologyNode(MonitorTopologyNode monitorTopologyNode) {
		this.monitorTopologyNode = monitorTopologyNode;
	}

	/** default constructor */
	public MonitorDevice() {
	}

	/** full constructor */
	public MonitorDevice(MonitorBuilding monitorBuilding,
			MonitorAlertPolicy monitorAlertPolicy,
			MonitorSwitchModel monitorSwitchModel,
			MonitorDeviceType monitorDeviceType,
			MonitorVendorMac monitorVendorMac,
			MonitorBuildingZone monitorBuildingZone, String ip,
			String readCommunity, String writeCommunity, String description,
			String name, String nameCn, String customTitle,
			String passwordLogin, String passwordEnable, String note1,
			String note2, String note3, String note4, Integer archiveUserNum, Integer snapUserNum, 
			Set monitorComputers,
			Set monitorSubnetDevices, Set monitorUrlServices, Set monitorVlans,
			Set monitorMacInfos, MonitorInterfaceCache monitorInterfaceCache,
			Set monitorServerProcesses) {
		this.monitorBuilding = monitorBuilding;
		this.monitorAlertPolicy = monitorAlertPolicy;
		this.monitorSwitchModel = monitorSwitchModel;
		this.monitorDeviceType = monitorDeviceType;
		this.monitorVendorMac = monitorVendorMac;
		this.monitorBuildingZone = monitorBuildingZone;
		this.ip = ip;
		this.readCommunity = readCommunity;
		this.writeCommunity = writeCommunity;
		this.description = description;
		this.name = name;
		this.nameCn = nameCn;
		this.customTitle = customTitle;
		this.passwordLogin = passwordLogin;
		this.passwordEnable = passwordEnable;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.archiveUserNum = archiveUserNum;
		this.snapUserNum = snapUserNum;
		this.monitorComputers = monitorComputers;
		this.monitorSubnetDevices = monitorSubnetDevices;
		this.monitorUrlServices = monitorUrlServices;
		this.monitorVlans = monitorVlans;
		this.monitorMacInfos = monitorMacInfos;
		this.monitorInterfaceCache = monitorInterfaceCache;
		this.monitorServerProcesses = monitorServerProcesses;
		// this.monitorSnapshots = monitorSnapshots;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorBuilding getMonitorBuilding() {
		return this.monitorBuilding;
	}

	public void setMonitorBuilding(MonitorBuilding monitorBuilding) {
		this.monitorBuilding = monitorBuilding;
	}

	public MonitorAlertPolicy getMonitorAlertPolicy() {
		return this.monitorAlertPolicy;
	}

	public void setMonitorAlertPolicy(MonitorAlertPolicy monitorAlertPolicy) {
		this.monitorAlertPolicy = monitorAlertPolicy;
	}

	public MonitorSwitchModel getMonitorSwitchModel() {
		return this.monitorSwitchModel;
	}

	public void setMonitorSwitchModel(MonitorSwitchModel monitorSwitchModel) {
		this.monitorSwitchModel = monitorSwitchModel;
	}

	public MonitorDeviceType getMonitorDeviceType() {
		return this.monitorDeviceType;
	}

	public void setMonitorDeviceType(MonitorDeviceType monitorDeviceType) {
		this.monitorDeviceType = monitorDeviceType;
	}

	public MonitorVendorMac getMonitorVendorMac() {
		return this.monitorVendorMac;
	}

	public void setMonitorVendorMac(MonitorVendorMac monitorVendorMac) {
		this.monitorVendorMac = monitorVendorMac;
	}

	public MonitorBuildingZone getMonitorBuildingZone() {
		return this.monitorBuildingZone;
	}

	public void setMonitorBuildingZone(MonitorBuildingZone monitorBuildingZone) {
		this.monitorBuildingZone = monitorBuildingZone;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReadCommunity() {
		return this.readCommunity;
	}

	public void setReadCommunity(String readCommunity) {
		this.readCommunity = readCommunity;
	}

	public String getWriteCommunity() {
		return this.writeCommunity;
	}

	public void setWriteCommunity(String writeCommunity) {
		this.writeCommunity = writeCommunity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getCustomTitle() {
		return this.customTitle;
	}

	public void setCustomTitle(String customTitle) {
		this.customTitle = customTitle;
	}

	public String getPasswordLogin() {
		return this.passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

	public String getPasswordEnable() {
		return this.passwordEnable;
	}

	public void setPasswordEnable(String passwordEnable) {
		this.passwordEnable = passwordEnable;
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
	
	public Integer getArchiveUserNum() {
		return this.archiveUserNum;
	}

	public void setArchiveUserNum(Integer archiveUserNum) {
		this.archiveUserNum = archiveUserNum;
	}
	
	public Integer getSnapUserNum() {
		return this.snapUserNum;
	}

	public void setSnapUserNum(Integer snapUserNum) {
		this.snapUserNum = snapUserNum;
	}

	public Set getMonitorComputers() {
		return this.monitorComputers;
	}

	public void setMonitorComputers(Set monitorComputers) {
		this.monitorComputers = monitorComputers;
	}

	public Set getMonitorSubnetDevices() {
		return monitorSubnetDevices;
	}

	public void setMonitorSubnetDevices(Set monitorSubnetDevices) {
		this.monitorSubnetDevices = monitorSubnetDevices;
	}

	public Set getMonitorUrlServices() {
		return this.monitorUrlServices;
	}

	public void setMonitorUrlServices(Set monitorUrlServices) {
		this.monitorUrlServices = monitorUrlServices;
	}

	public Set getMonitorVlans() {
		return this.monitorVlans;
	}

	public void setMonitorVlans(Set monitorVlans) {
		this.monitorVlans = monitorVlans;
	}

	public Set getMonitorMacInfos() {
		return this.monitorMacInfos;
	}

	public void setMonitorMacInfos(Set monitorMacInfos) {
		this.monitorMacInfos = monitorMacInfos;
	}

	public MonitorInterfaceCache getMonitorInterfaceCache() {
		return this.monitorInterfaceCache;
	}

	public void setMonitorInterfaceCache(
			MonitorInterfaceCache monitorInterfaceCache) {
		this.monitorInterfaceCache = monitorInterfaceCache;
	}

	public Set getMonitorServerProcesses() {
		return this.monitorServerProcesses;
	}

	public void setMonitorServerProcesses(Set monitorServerProcesses) {
		this.monitorServerProcesses = monitorServerProcesses;
	}
//
//	public Set getMonitorSnapshots() {
//		return this.monitorSnapshots;
//	}
//
//	public void setMonitorSnapshots(Set monitorSnapshots) {
//		this.monitorSnapshots = monitorSnapshots;
//	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCpuOid() {
		return cpuOid;
	}

	public void setCpuOid(String cpuOid) {
		this.cpuOid = cpuOid;
	}

	public MonitorV3Params getMonitorV3Params() {
		return monitorV3Params;
	}

	public void setMonitorV3Params(MonitorV3Params monitorV3Params) {
		this.monitorV3Params = monitorV3Params;
	}

	 

}