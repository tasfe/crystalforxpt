package com.combanc.monitor.pojo;

/**
 * MonitorSegment entity. @author MyEclipse Persistence Tools
 */

public class MonitorSegment implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorSubnet monitorSubnet;
	private String networkSegment;
	private String mask;
	private String connectedDevice;
	private String devicePort;
	private String deviceInterface;
	private String interfaceDescription;
	private String nextHop;
	private String type;
	private String note1;
	private String note2;
	private String note3;
	private String note4;

	// Constructors

	/** default constructor */
	public MonitorSegment() {
	}

	/** full constructor */
	public MonitorSegment(MonitorSubnet monitorSubnet, String networkSegment, String mask,
			String connectedDevice, String devicePort, String deviceInterface,
			String interfaceDescription, String nextHop, String type,
			String note1, String note2, String note3, String note4) {
		this.monitorSubnet = monitorSubnet;
		this.networkSegment = networkSegment;
		this.mask = mask;
		this.connectedDevice = connectedDevice;
		this.devicePort = devicePort;
		this.deviceInterface = deviceInterface;
		this.interfaceDescription = interfaceDescription;
		this.nextHop = nextHop;
		this.type = type;
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

	public String getNetworkSegment() {
		return this.networkSegment;
	}

	public void setNetworkSegment(String networkSegment) {
		this.networkSegment = networkSegment;
	}

	public String getMask() {
		return this.mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getConnectedDevice() {
		return this.connectedDevice;
	}

	public void setConnectedDevice(String connectedDevice) {
		this.connectedDevice = connectedDevice;
	}

	public String getDevicePort() {
		return this.devicePort;
	}

	public void setDevicePort(String devicePort) {
		this.devicePort = devicePort;
	}

	public String getDeviceInterface() {
		return this.deviceInterface;
	}

	public void setDeviceInterface(String deviceInterface) {
		this.deviceInterface = deviceInterface;
	}

	public String getInterfaceDescription() {
		return this.interfaceDescription;
	}

	public void setInterfaceDescription(String interfaceDescription) {
		this.interfaceDescription = interfaceDescription;
	}

	public String getNextHop() {
		return this.nextHop;
	}

	public void setNextHop(String nextHop) {
		this.nextHop = nextHop;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

}