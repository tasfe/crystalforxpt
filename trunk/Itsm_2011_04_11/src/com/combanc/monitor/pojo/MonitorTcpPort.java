package com.combanc.monitor.pojo;

/**
 * MonitorTcpPort entity. @author MyEclipse Persistence Tools
 */

public class MonitorTcpPort implements java.io.Serializable {

	// Fields
	/**端口号**/
	private String portNumber;
	/**端口名**/
	private String portName;
	/**端口类型**/
	private String portType;

	// Constructors

	/** default constructor */
	public MonitorTcpPort() {
	}

	/** full constructor */
	public MonitorTcpPort(String portName, String portType) {
		this.portName = portName;
		this.portType = portType;
	}

	// Property accessors

	public String getPortNumber() {
		return this.portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getPortName() {
		return this.portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getPortType() {
		return this.portType;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

}