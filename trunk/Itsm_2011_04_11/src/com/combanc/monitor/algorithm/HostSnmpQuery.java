package com.combanc.monitor.algorithm;

import java.util.List;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorDevice;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */
public class HostSnmpQuery {
	SnmpTarget target = new SnmpTarget();
	String displayStr;
	
	public HostSnmpQuery(){
		displayStr = "读 ";
		target.setLoadFromCompiledMibs(true);
		target.setTimeout(MainConstants.SNMP_TIME_OUT);
		target.setRetries(MainConstants.SNMP_RETRY);
	}
	/**获取服务器所有进程的名称**/
	public String getProcessName(MonitorDevice device,List<String> list) {
		target.setTargetHost(device.getIp());
		target.setCommunity(device.getReadCommunity());
		int readCount = 0, indexOidLen;
		String result = "";
		String tempStr, oidStr;
		boolean firstRecord = true;
		String oid = ".1.3.6.1.2.1.25.4.2.1.2";
		indexOidLen = oid.length();

		target.setObjectID(oid);
		while (true) {
			result = target.snmpGetNext();
			if (result == null) {
				tempStr = displayStr + "服务器进程表SNMP超时。";
				System.out.println(tempStr);
				return "超时";
			}

			oidStr = target.getSnmpOID().toString();
			if (oidStr.length() < indexOidLen) {
				if (firstRecord) {
					// 表空表明不支持该特性：
					tempStr = displayStr + "服务器进程表：MIB不支持该特性。";
					System.out.println(tempStr);
					return "不支持该特性";
				} else {
					System.out.println(displayStr + device + "服务器进程表完毕。");
					return "OK";
				}
			}

			tempStr = oidStr.substring(0, indexOidLen);
			if (!tempStr.equals(oid)) {
				if (firstRecord) {
					// 表空表明不支持该特性：
					tempStr = displayStr + device + "服务器进程表：MIB不支持该特性。";
					System.out.println(tempStr);
					return "不支持该特性";
				} else {
					System.out.println(displayStr + device + "服务器进程表完毕。");
					return "OK";
				}
			}
			readCount++;
			firstRecord = false;
			list.add(result);
		}
	}

}
