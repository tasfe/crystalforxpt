package com.combanc.monitor.pojoext;

/**
 * @author lsj
 * 
 * 原IETFV6表
 */
public class Ietfv6Entry {

//	FIELD0 = 接口,Variant.STRING,-1,-1,
//	FIELD1 = 接收包,Variant.STRING,-1,-1,
//	FIELD2 = 接收丢包,Variant.STRING,-1,-1,
//	FIELD3 = 接收多播包,Variant.STRING,-1,-1,
//	FIELD4 = 发送包,Variant.STRING,-1,-1,
//	FIELD5 = 发送丢包,Variant.STRING,-1,-1,
//	FIELD6 = 发送多播包,Variant.STRING,-1,-1,
	
	String interface_;
	String rxReceive;
	String rxDiscard;
	String rxMulticast;
	String txReceive;
	String txDiscard;
	String txMulticast;
	
	public Ietfv6Entry() {
	}

	public String getInterface_() {
		return interface_;
	}

	public void setInterface_(String interface1) {
		interface_ = interface1;
	}

	public String getRxReceive() {
		return rxReceive;
	}

	public void setRxReceive(String rxReceive) {
		this.rxReceive = rxReceive;
	}

	public String getRxDiscard() {
		return rxDiscard;
	}

	public void setRxDiscard(String rxDiscard) {
		this.rxDiscard = rxDiscard;
	}

	public String getRxMulticast() {
		return rxMulticast;
	}

	public void setRxMulticast(String rxMulticast) {
		this.rxMulticast = rxMulticast;
	}

	public String getTxReceive() {
		return txReceive;
	}

	public void setTxReceive(String txReceive) {
		this.txReceive = txReceive;
	}

	public String getTxDiscard() {
		return txDiscard;
	}

	public void setTxDiscard(String txDiscard) {
		this.txDiscard = txDiscard;
	}

	public String getTxMulticast() {
		return txMulticast;
	}

	public void setTxMulticast(String txMulticast) {
		this.txMulticast = txMulticast;
	}
}
