package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorRealtimePortflow entity. @author MyEclipse Persistence Tools
 */

public class MonitorRealtimePortflow implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 552722908525549051L;
	private Integer id;
	private String deviceIp;
	private String deviceName;
	private String deviceType;
	private String interface_;
	private String interfaceDescription;
	private Integer isLinkport;
	private Long txByte;
	private Long rxByte;
	private Long bilateralTraffic;
	private Long deliveryTraffic;
	private Long receiveTraffic;
	private Timestamp gatherTime;

	// Constructors

	/** default constructor */
	public MonitorRealtimePortflow() {
	}

	/** full constructor */
	public MonitorRealtimePortflow(String deviceIp, String deviceName,String deviceType,
			String interface_, String interfaceDescription, 
			Integer isLinkport,Long bilateralTraffic, 
			Long deliveryTraffic, Long receiveTraffic,
			Timestamp gatherTime) {
		this.deviceIp = deviceIp;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.interface_ = interface_;
		this.interfaceDescription = interfaceDescription;
		this.isLinkport = isLinkport;
		this.bilateralTraffic = bilateralTraffic;
		this.deliveryTraffic = deliveryTraffic;
		this.receiveTraffic = receiveTraffic;
		this.gatherTime = gatherTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getInterface_() {
		return interface_;
	}

	public void setInterface_(String interface1) {
		interface_ = interface1;
	}

	public Integer getIsLinkport() {
		return isLinkport;
	}

	public void setIsLinkport(Integer isLinkport) {
		this.isLinkport = isLinkport;
	}

	public Long getBilateralTraffic() {
		return bilateralTraffic;
	}

	public void setBilateralTraffic(Long bilateralTraffic) {
		this.bilateralTraffic = bilateralTraffic;
	}

	public Long getDeliveryTraffic() {
		return deliveryTraffic;
	}

	public void setDeliveryTraffic(Long deliveryTraffic) {
		this.deliveryTraffic = deliveryTraffic;
	}

	public Long getReceiveTraffic() {
		return receiveTraffic;
	}

	public void setReceiveTraffic(Long receiveTraffic) {
		this.receiveTraffic = receiveTraffic;
	}

	public Timestamp getGatherTime() {
		return gatherTime;
	}

	public void setGatherTime(Timestamp gatherTime) {
		this.gatherTime = gatherTime;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getInterfaceDescription() {
		return interfaceDescription;
	}

	public void setInterfaceDescription(String interfaceDescription) {
		this.interfaceDescription = interfaceDescription;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Long getTxByte() {
		return txByte;
	}

	public void setTxByte(Long txByte) {
		this.txByte = txByte;
	}

	public Long getRxByte() {
		return rxByte;
	}

	public void setRxByte(Long rxByte) {
		this.rxByte = rxByte;
	}

	 

	 

}