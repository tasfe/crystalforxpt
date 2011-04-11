package com.combanc.monitor.algorithm.tool;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.OidConstants;
import com.combanc.monitor.dao.MonitorPingDestDAO;
import com.combanc.monitor.pojo.MonitorVendor;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorVendorService;
import com.combanc.monitor.util.ApplicationContextUtil;

public class CpuPollTool {

	private static final Log log = LogFactory.getLog(CpuPollTool.class);
	SnmpTarget snmpTarget;;
	MonitorDeviceService deviceService;
	MonitorVendorService monitorVendorService;
	SnmpTool snmpTool;
	
	String h3cOid = "";
	
	public CpuPollTool() {
		snmpTarget = new SnmpTarget();
		snmpTool = new SnmpTool(snmpTarget);
		deviceService = (MonitorDeviceService) ApplicationContextUtil.getContext().getBean("MonitorDeviceService");
		monitorVendorService = (MonitorVendorService) ApplicationContextUtil.getContext().getBean("MonitorVendorService");
		
	}

	/**
	 * 获取CPU值，注意，如果传入了savedOid，则先用这个读取，读取失败或者并没有传入此参数，则实际读取
	 * @param ip
	 * @param commStr
	 * @param savedOid	这个参数是在拓扑图、一览表里面回了防止每次都去获取cpuOid，而存储在内存中的值，在其它地方，此参数传入为null
	 * @return
	 */
	public long getCpuLoad(String ip, String commStr, String savedOid) {
		 
		long ret = -1;
		if (MainConstants.DEMO_MODE) {
			Random rdm1 = new Random();
			Random rdm = new Random(System.currentTimeMillis()
					/ (rdm1.nextInt() + 1));// 产生随机数种子，与时间和行号关联
			return (long) (rdm.nextInt(100));
		} else {
			// 1、先用传入的oid读取，防止频繁读取厂商表，或读取H3C的实体表
			if(savedOid != null && !"".equals(savedOid)) {
				ret = getCpu(ip, commStr, savedOid);
				if(ret >= 0 && ret <= 100)
					return ret;
			}
			// 2、非H3C设备，从厂商表获取oid读取
			String cpuOid = getVendorCpuOid(ip, commStr);
			ret = getCpu(ip, commStr, cpuOid);
			// 3、H3C设备，判断厂商表中的oid读取结果是否正确，正确返回，不正确则使用H3C的方法在尝试读取
			if (isH3cOid(cpuOid)) {
				// 如果使用厂商表中的oid可以读取到cpu，将此值付给h3cOid
				if(ret >= 0 && ret <= 100) {
					log.info(ip + " >>>> 直接从厂商表读取，oid为：" + cpuOid);
					h3cOid = cpuOid;
				}
				else {
					String vendor = "25506";
					if(cpuOid.indexOf("2011") >= 0)
						vendor = "2011";
					ret = getH3cCpu(ip, commStr, vendor);
				}
			}
			return ret;
		}
	}
	
	/**
	 *  取节点的CPU和响应/运行时间，并将数据保存到节点表的相应位置。
	 * @param ip
	 * @param commStr
	 * @param cpuOid
	 * @return
	 */
	public long getCpu(String ip, String commStr, String cpuOid) {
		String result = "";
		long ret = -1;
		// 首先读取系统运行时间并获得响应时间
		snmpTarget.setTimeout(1);
		snmpTarget.setRetries(1);
		snmpTarget = snmpTool.initSnmpTarget(ip);
//		snmpTarget.setTargetHost(ip);
//		snmpTarget.setCommunity(commStr);
//		snmpTarget.setObjectID(".1.3.6.1.2.1.1.3.0");// mib-2.system.sysUpTime
//		long timeUsed = System.currentTimeMillis();
//		String result = snmpTarget.snmpGet();
//		timeUsed = System.currentTimeMillis() - timeUsed;

		// 然后读取CPULOAD
		snmpTarget.setObjectID(cpuOid);
		// 取OID的最后一位
		String last = cpuOid.substring(cpuOid.lastIndexOf(".") + 1);
		if (last.equals("0")) // CPUOID为叶节点，如CISCO，使用GET方法
			result = snmpTarget.snmpGet();
		// Harbor
		else if (cpuOid.equals(".1.3.6.1.4.1.8212.1.1.4.1.1.4.1"))
			result = snmpTarget.snmpGet();
		else
			// 为非叶节点，取第一项，如MS，使用GETNEXT方法
			result = snmpTarget.snmpGetNext();

		int errorCode = snmpTarget.getErrorCode();
		int errorIndex = snmpTarget.getErrorIndex();
		if (errorCode != 0) {
			if (errorCode == 22 && errorIndex == 0)
				return -1;
			return -1;
		}
		ret = checkCpuData(result);

		// 主机UCD-MIB的CPU定义为IDLE，这里转化为BUSY。
		// private.enterprises.ucdavis.systemStats.ssCpuIdle
		if (cpuOid.equals(".1.3.6.1.4.1.2021.11.11.0"))
			ret = 100 - ret;
		return ret;
	}
	/**
	 * 将获取的Cpu result转换成long，转换失败，或者转换后的值不在0~100之间，则返回-1
	 * @param result
	 * @return
	 */
	private long checkCpuData(String result) {
		long ret;
		try {
			ret = Long.parseLong(result);
		} catch (NumberFormatException e) {
			ret = -1;
		}
		if (ret < 0 || ret > 100)
			ret = -1;
		return ret;
	}
	
 
	
	/**
	 * 读取厂商代码，然后从厂商表中查找CPUOID，不分主机或设备，默认为UCD-SNMP
	 * @param mDevice	设备对象
	 * @return			对应的CpuOid
	 */
	public String getVendorCpuOid(String ip, String commStr) {
		if (MainConstants.DEMO_MODE) 
			return ".1.3.6.1.4.1.9.2.1.56.0";// 演示模式
		
		 
		String vendor = getVendor(ip,commStr);
		if (vendor == null)
			return "超时";// 如果没有读出，则使用CISCO CPUOID，否则不发包响应时间为0。
		 
		MonitorVendor monitorVendor =monitorVendorService.findByVendor(vendor);
		if(monitorVendor != null)
			return monitorVendor.getCpuOid();
		else
			return ".1.3.6.1.4.1.2021.11.11.0";// 如果没有则缺省使用UCD-SNMP的，但不能为空，否则不发包响应时间为0。
	}
	
	public String getVendor(String ip, String commStr) {
		 
		String result;
		snmpTarget = snmpTool.initSnmpTarget(ip);
		snmpTarget.setObjectID(OidConstants.SYSTEM_OBJECT_ID);
		result = snmpTarget.snmpGet();
		if (result == null) {
			return null;
		}
		// if( Result.length() <= 42 ) return "0.0";//返回值错误，DCS7608。
		if (result.length() <= 42 && !result.startsWith(".1.3.6.1.4.1")) {
			return "0.0";// 返回值错误，DCS7608。
		}
		if (result.startsWith(".1.3.6.1.4.1")) {
			result = result.substring(13);
		} else {
			result = result.substring(42);// 去掉.iso.org.dod.internet.private.enterprises.前缀
		}
		if (result.indexOf(".") != -1) {// SUN去掉前缀后只有OID43，所以要判断是否还有后缀
			result = result.substring(0, result.indexOf("."));// 取得厂商OID
		}
		// 注：如果装载了CISCO-VTP/CPU-MIB，则厂商OID为cisco而不是9，因为可以将9解释为cisco
		return result;
	}
	/**是否是H3C**/
	public boolean isH3cOid(String cpuOid) {
		return cpuOid.indexOf("25506") >= 0 || cpuOid.indexOf("2011") >= 0;
	}
	
	/**读H3c设备的cpu**/
	private long getH3cCpu(String ip, String commStr, String vendor) {
		Log logger = LogFactory.getLog(CpuPollTool.class);
		h3cOid = "";
		String cpuOid = "";
		String result = "";
		long ret = -1;
		// 1、25505的设备首先用".1.3.6.1.4.1.2011.10.2.6.1.1.1.1.6.7"尝试读取
		if("25506".equals(vendor)) {
			ret = getCpu(ip, commStr, ".1.3.6.1.4.1.2011.10.2.6.1.1.1.1.6.7");
			logger.info(ip + "  按照.1.3.6.1.4.1.2011.10.2.6.1.1.1.1.6.7 常识读取CPU，读取结果： " + ret);
			if(ret >= 0 && ret <= 100) {
				h3cOid = ".1.3.6.1.4.1.2011.10.2.6.1.1.1.1.6.7";
				return ret;
			}
		}
		
		// 2、使用实体表获取CUP实体的索引，并按照25506的mib进行读取
		cpuOid = getH3cOid(ip, commStr);
		if(!isH3cOid(cpuOid))
			return -1;
		ret = getCpu(ip, commStr, cpuOid);
		logger.info(ip + "  根据CPU实体的索引来读取CPU，cpuOid为 " + cpuOid + "，读取结果： " + ret);
		if(ret >= 0 && ret <= 100) {
			h3cOid = cpuOid;
			return ret;
		}
		
		// 3、使用2011替换25506
		cpuOid = cpuOid.replaceFirst("25506", "2011");
		ret = getCpu(ip, commStr, cpuOid);
		logger.info(ip + "  替换25506为2011， " + cpuOid + "，读取结果： " + ret);
		if(ret >= 0 && ret <= 100) {
			h3cOid = cpuOid;
			return ret;
		}
		
		return -1;
	}
	
	// 根据H3C的实体表，获取其CPU Oid
	public String getH3cOid(String ip, String commStr) {
		Log logger = LogFactory.getLog(CpuPollTool.class);
		String displayStr = "读取";
		String[] oids = { OidConstants.ENTITY_CLASS, OidConstants.ENTITY_DESCR, OidConstants.ENTITY_NAME };
		String retOid = ".1.3.6.1.4.1.25506.2.6.1.1.1.1.6.";
		
		String tableName = "设备实体表";
		int readCount = 0;
		String result[] = new String[oids.length];
		String indexOid, tempStr, oidStr;
		int indexOidLen;
		boolean firstRecord = true;

		snmpTarget = snmpTool.initSnmpTarget(ip);
//		snmpTarget.setTargetHost(ip);
//		snmpTarget.setCommunity(commStr);
		indexOid = oids[0];
		indexOidLen = indexOid.length();
		snmpTarget.setObjectIDList(oids);

		String oid = "";
		logger.info(displayStr + ip + " " + tableName + "...");
		while (true) {
			result = snmpTarget.snmpGetNextList();
			oid = snmpTarget.getSnmpOID().toString();
			if (result == null) {
				logger.info(displayStr + ip + " " + tableName + "SNMP超时。");
				return "无此变量";
			}

			oidStr = snmpTarget.getSnmpOID().toString();
			if (oidStr.length() < indexOidLen) {
				if (firstRecord) {
					logger.info(displayStr + ip + " MIB中没有" + tableName + "或表空。");
					return "表空";
				} else {
					logger.info(displayStr + ip + " " + tableName + "完毕。");
					return "OK";
				}
			}

			tempStr = oidStr.substring(0, indexOidLen);
			if (!tempStr.equals(indexOid)) {
				if (firstRecord) {
					logger.info(displayStr + ip + " MIB中没有" + tableName + "或表空。");
					return "表空";
				} else {
					logger.info(displayStr + ip + " " + tableName + "完毕。");
					return "OK";
				}
			}

			readCount++;
			logger.info(displayStr + ip + " " + tableName + " " + readCount	+ " 条记录。");
			firstRecord = false;

			// 这两个表使用OID，所以在这里保存
			if((result[0].equals("module(9)") || result[0].equals("9"))
					&& !result[2].contains("virtual")) {
				String nStr = oid.substring(oid.lastIndexOf(".") + 1);
				// 因为使用GetNext读取，这里把实体的N值先减1
				int nValue = 0;
				try {
					nValue = Integer.parseInt(nStr);
					if(nValue > 0)
						nStr = String.valueOf(nValue - 1);
				}catch(NumberFormatException nfe) {
				}
				retOid += nStr;
 				return retOid;
			}
			firstRecord = false;
		}// while
	}
	
	
	/** 
	 * 取AKCP节点的温湿度
	 * @param target
	 * @param ip
	 * @param commStr
	 * @return
	 */
	public float[] getAkcpData(SnmpTarget target, String ip, String commStr) {
		float lResult[] = new float[2];
		// 首先读取系统运行时间并获得响应时间
		target.setTimeout(1);
		target.setRetries(0);
		snmpTarget = snmpTool.initSnmpTarget(ip);
//		target.setTargetHost(ip);
//		target.setCommunity(commStr);
		target.setObjectID(".1.3.6.1.2.1.1.3.0");// mib-2.system.sysUpTime
		long timeUsed = System.currentTimeMillis();
		String result = target.snmpGet();
		timeUsed = System.currentTimeMillis() - timeUsed;

		String oidTemp = OidConstants.AKCP_TEMPERATURE;
		String oidHumi = OidConstants.AKCP_HUMILITY;
		// 然后读取CPULOAD
		target.setObjectID(oidTemp);
		// 取OID的最后一位
		result = target.snmpGet();
//		int errorCode = target.getErrorCode();
//		int errorIndex = target.getErrorIndex();
//		if (errorCode != 0) {
//			if (errorCode == 22 && errorIndex == 0)
//				lResult[0] = -1;
//			lResult[0] = -1;
//		}

		try {
			if (result == null) {
				lResult[0] = -1;
			} else {
				lResult[0] = Float.parseFloat(result);
			}
		} catch (NumberFormatException e) {
			lResult[0] = -1;
		}

		target.setObjectID(oidHumi);
		// 取OID的最后一位
		result = target.snmpGet();
		try {
			if (result == null) {
				lResult[1] = -1;
			} else {
				lResult[1] = Float.parseFloat(result);
			}
		} catch (NumberFormatException e) {
			lResult[1] = -1;
		}
		return lResult;
	}
	public SnmpTarget getSnmpTarget() {
		return snmpTarget;
	}

	public void setSnmpTarget(SnmpTarget snmpTarget) {
		this.snmpTarget = snmpTarget;
	}

	public MonitorDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(MonitorDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public SnmpTool getSnmpTool() {
		return snmpTool;
	}

	public void setSnmpTool(SnmpTool snmpTool) {
		this.snmpTool = snmpTool;
	}

	public String getH3cOid() {
		return h3cOid;
	}

	public void setH3cOid(String h3cOid) {
		this.h3cOid = h3cOid;
	}
	
}
