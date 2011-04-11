package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * MonitorRegularDataDay entity. @author MyEclipse Persistence Tools
 */

public class MonitorRegularDataDay extends MonitorBaseRegularData  implements java.io.Serializable {

	// Fields

	 
	private Long txByte;
	private Long rxByte;
	private Long maxBiTraffic;
	private Long minBiTraffic;
	private Long maxDeliveryTraffic;
	private Long minDeliveryTraffic;
	private Long maxReceiveTraffic;
	private Long minReceiveTraffic;

	// Constructors

	/** default constructor */
	public MonitorRegularDataDay() {
	}

	/** full constructor */
	public MonitorRegularDataDay(String ip, String interface_, Long txByte,
			Long rxByte, Long biTraffic, Long maxBiTraffic, Long minBiTraffic,
			Long deliveryTraffic, Long maxDeliveryTraffic,
			Long minDeliveryTraffic, Long receiveTraffic,
			Long maxReceiveTraffic, Long minReceiveTraffic, Timestamp gatherTime) {
		this.ip = ip;
		this.interface_ = interface_;
		this.txByte = txByte;
		this.rxByte = rxByte;
		this.biTraffic = biTraffic;
		this.maxBiTraffic = maxBiTraffic;
		this.minBiTraffic = minBiTraffic;
		this.deliveryTraffic = deliveryTraffic;
		this.maxDeliveryTraffic = maxDeliveryTraffic;
		this.minDeliveryTraffic = minDeliveryTraffic;
		this.receiveTraffic = receiveTraffic;
		this.maxReceiveTraffic = maxReceiveTraffic;
		this.minReceiveTraffic = minReceiveTraffic;
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

	public Long getMaxBiTraffic() {
		return maxBiTraffic;
	}

	public void setMaxBiTraffic(Long maxBiTraffic) {
		this.maxBiTraffic = maxBiTraffic;
	}

	public Long getMinBiTraffic() {
		return minBiTraffic;
	}

	public void setMinBiTraffic(Long minBiTraffic) {
		this.minBiTraffic = minBiTraffic;
	}

	public Long getMaxDeliveryTraffic() {
		return maxDeliveryTraffic;
	}

	public void setMaxDeliveryTraffic(Long maxDeliveryTraffic) {
		this.maxDeliveryTraffic = maxDeliveryTraffic;
	}

	public Long getMinDeliveryTraffic() {
		return minDeliveryTraffic;
	}

	public void setMinDeliveryTraffic(Long minDeliveryTraffic) {
		this.minDeliveryTraffic = minDeliveryTraffic;
	}

	public Long getMaxReceiveTraffic() {
		return maxReceiveTraffic;
	}

	public void setMaxReceiveTraffic(Long maxReceiveTraffic) {
		this.maxReceiveTraffic = maxReceiveTraffic;
	}

	public Long getMinReceiveTraffic() {
		return minReceiveTraffic;
	}

	public void setMinReceiveTraffic(Long minReceiveTraffic) {
		this.minReceiveTraffic = minReceiveTraffic;
	}

	// Property accessors

	

}