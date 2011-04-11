package com.combanc.monitor.vo.snmp.entity;

public class TrapV2PDU extends SnmpPDU {
	private static final long serialVersionUID = 1L;

	public TrapV2PDU(String paramString) {
		setType(-89);
		addVarBind(".1.3.6.1.6.3.1.1.4.1.0", paramString, 0);
	}
}