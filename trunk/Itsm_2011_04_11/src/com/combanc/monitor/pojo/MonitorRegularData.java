package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorRegularData entity. @author MyEclipse Persistence Tools
 */

public class MonitorRegularData extends MonitorBaseRegularData implements java.io.Serializable {

	// Fields


	private Long txByte ;
	private Long rxByte;


	// Constructors

	/** default constructor */
	public MonitorRegularData() {
	}

	/** full constructor */
	public MonitorRegularData(String ip, String interface_, Long txByte,
			Long rxByte, Long biTraffic, Long deliveryTraffic,
			Long receiveTraffic, Timestamp gatherTime) {
		this.ip = ip;
		this.interface_ = interface_;
		this.txByte = txByte;
		this.rxByte = rxByte;
		this.biTraffic = biTraffic;
		this.deliveryTraffic = deliveryTraffic;
		this.receiveTraffic = receiveTraffic;
		this.gatherTime = gatherTime;
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