package com.combanc.monitor.vo.snmp.entity;

import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.snmp4j.PDU;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

public class SnmpPDU extends PDU {
	private final Log logger = LogFactory.getLog(super.getClass());
	private boolean debug = false;
	private int version;

	public SnmpPDU() {
	}

	public SnmpPDU(PDU paramPDU) {
		try {
			BeanUtils.copyProperties(this, paramPDU);
			Iterator localIterator = paramPDU.getVariableBindings().iterator();
			while (localIterator.hasNext())
				add((VariableBinding) localIterator.next());
		} catch (Exception localException) {
		}
	}

	public void addIntegerVarBind(String paramString1, String paramString2) {
		VariableBinding localVariableBinding = new VariableBinding(new OID(
				paramString1), new Integer32(Integer.parseInt(paramString2)));
		add(localVariableBinding);
	}

	public boolean addVarBind(String paramString1, String paramString2,
			int paramInt) {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("oid=" + paramString1);
			this.logger.debug("val=" + paramString2);
		}
		switch (paramInt) {
		case 1:
			addIntegerVarBind(paramString1, paramString2);
			break;
		default:
			addStringVarBind(paramString1, paramString2);
		}
		return true;
	}

	public void addStringVarBind(String paramString1, String paramString2,
			String paramString3) {
		if (this.logger.isDebugEnabled())
			this.logger.debug("Set VarBind{" + paramString1 + ":"
					+ paramString2 + "}");
		VariableBinding localVariableBinding = new VariableBinding(new OID(
				paramString1), new OctetString(paramString2));
		add(localVariableBinding);
	}

	public void addStringVarBind(String paramString1, String paramString2) {
		addStringVarBind(paramString1, paramString2, "GBK");
	}

	public void addNull(String paramString) {
		VariableBinding localVariableBinding = new VariableBinding(new OID(
				paramString), new Null());
		add(localVariableBinding);
	}

	public boolean isDebug() {
		return this.debug;
	}

	public void setDebug(boolean paramBoolean) {
		this.debug = paramBoolean;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int paramInt) {
		this.version = paramInt;
	}

	public void setCommand(int paramInt) {
		setType(paramInt);
	}
}