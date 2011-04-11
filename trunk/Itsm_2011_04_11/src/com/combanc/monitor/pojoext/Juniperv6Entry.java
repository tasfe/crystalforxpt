package com.combanc.monitor.pojoext;

/**
 * @author lsj
 * 
 * 原JUNIPERV6表
 */
public class Juniperv6Entry {

//	FIELD0 = 接收报文,Variant.STRING,-1,-1,
//	FIELD1 = 发送报文,Variant.STRING,-1,-1,
	
	String receive;
	String transmit;
	
	public Juniperv6Entry() {
	}
	
	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getTransmit() {
		return transmit;
	}

	public void setTransmit(String transmit) {
		this.transmit = transmit;
	}
}
