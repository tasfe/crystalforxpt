package com.combanc.monitor.pojoext;

/**
 * @author lsj
 *
 * 原NETFLOW表
 */
public class NetflowEntry {

//	FIELD0 = 源地址,Variant.STRING,-1,-1,
//	FIELD1 = 源端口,Variant.STRING,-1,-1,
//	FIELD2 = 目的地址,Variant.STRING,-1,-1,
//	FIELD3 = 目的端口,Variant.STRING,-1,-1,
//	FIELD4 = 协议,Variant.STRING,-1,-1,
//	FIELD5 = 流量,Variant.STRING,-1,-1,
	String srcAddress;
	String srcPort;
	String dstAddress;
	String dstPort;
	String protocol;
	String flow;
	
	public NetflowEntry() {
	}

	public String getSrcAddress() {
		return srcAddress;
	}
	public void setSrcAddress(String srcAddress) {
		this.srcAddress = srcAddress;
	}
	public String getSrcPort() {
		return srcPort;
	}
	public void setSrcPort(String srcPort) {
		this.srcPort = srcPort;
	}
	public String getDstAddress() {
		return dstAddress;
	}
	public void setDstAddress(String dstAddress) {
		this.dstAddress = dstAddress;
	}
	public String getDstPort() {
		return dstPort;
	}
	public void setDstPort(String dstPort) {
		this.dstPort = dstPort;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
}
