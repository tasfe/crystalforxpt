package com.combanc.monitor.pojo;

import java.sql.Timestamp;

/**
 * 
 * @author Administrator
 *
 */
public class MonitorBaseRegularData {

	protected Integer id;
	protected String ip;
	protected String interface_;
	protected Long biTraffic;
	protected Long deliveryTraffic;
	protected Long receiveTraffic;
	protected Timestamp gatherTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getInterface_() {
		return interface_;
	}
	public void setInterface_(String interface1) {
		interface_ = interface1;
	}
	public Long getBiTraffic() {
		return biTraffic;
	}
	public void setBiTraffic(Long biTraffic) {
		this.biTraffic = biTraffic;
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
	
	
}
